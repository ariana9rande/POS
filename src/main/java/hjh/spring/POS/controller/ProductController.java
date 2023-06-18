package hjh.spring.POS.controller;

import hjh.spring.POS.model.*;
import hjh.spring.POS.service.BalanceService;
import hjh.spring.POS.service.LogService;
import hjh.spring.POS.service.ProductService;
import hjh.spring.POS.service.SaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private final ProductService productService;
    private final SaleService saleService;
    private final BalanceService balanceService;
    private final LogService logService;

    public ProductController(ProductService productService, SaleService saleService, BalanceService balanceService, LogService logService)
    {
        this.productService = productService;
        this.saleService = saleService;
        this.balanceService = balanceService;
        this.logService = logService;
    }

    @GetMapping("/register")
    public String productManage(Model model, HttpSession session)
    {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember == null || !loginMember.getRole().equals("매니저"))
        {
            model.addAttribute("errorMessage", "권한이 없습니다.");
            return "/authError";
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "productRegister";
    }

    @PostMapping("/register")
    public String registerProduct(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock,
            Model model, HttpSession session
    )
    {
        List<Product> products = productService.getAllProducts();

        if (name != null)
        {
            for (Product product : products)
            {
                if (product.getName().equals(name))
                {
                    model.addAttribute("error", "이미 존재하는 상품입니다. 입고를 이용해주세요");
                    model.addAttribute("products", products);
                    return "productRegister";
                }
            }
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setPurchasePrice((int)(price * 0.3));

        productService.registerProduct(product);
        model.addAttribute("product", product);

        Balance balance = balanceService.findFirstBalance();
        balance.setAmount(balance.getAmount() - product.getPurchasePrice() * stock);
        balanceService.updateBalance(balance);

        Log log = new Log();
        log.setAction("register");
        log.setProduct(product);
        log.setChangeStock(stock);
        log.setChangeBalance(-(product.getPurchasePrice() * stock));

        logService.saveLog(log);

        session.setAttribute("balance", balance);

        return "productRegisterSuccess";
    }

    @GetMapping("/products")
    public String products(Model model, HttpSession session)
    {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember == null)
        {
            model.addAttribute("errorMessage", "권한이 없습니다.");
            return "/authError";
        }
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/add")
    public String productAddForm(Model model, HttpSession session)
    {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember == null || !loginMember.getRole().equals("매니저"))
        {
            model.addAttribute("errorMessage", "권한이 없습니다.");
            return "/authError";
        }

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "productAdd";
    }

    @PostMapping("/add")
    public String productAdd(@RequestParam("productId") Long productId,
                             @RequestParam("quantity") int quantity,
                             Model model, HttpSession session)
    {
        productService.addProductStock(productId, quantity);
        Product product = productService.findProductById(productId);

        Balance balance = balanceService.findFirstBalance();
        balance.setAmount(balance.getAmount() - product.getPurchasePrice() * quantity);
        balanceService.updateBalance(balance);

        Log log = new Log();
        log.setAction("add");
        log.setProduct(product);
        log.setChangeStock(quantity);
        log.setChangeBalance(-(product.getPurchasePrice() * quantity));

        logService.saveLog(log);

        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);

        session.setAttribute("balance", balance);

        return "productAddSuccess";
    }

    @GetMapping("/sell")
    public String productSellForm(Model model, HttpSession session)
    {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember == null)
        {
            model.addAttribute("errorMessage", "권한이 없습니다.");
            return "/authError";
        }

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "sell";
    }


    @PostMapping("/sell")
    public String addToSellList(@RequestParam("productId") Long productId,
                                @RequestParam("quantity") int quantity,
                                HttpSession session,
                                Model model)
    {
        Sale sale = (Sale) session.getAttribute("sale");

        if (sale == null)
        {
            sale = new Sale();
            saleService.saveSale(sale);
            session.setAttribute("sale", sale);
        }

        Product product = productService.findProductById(productId);

        boolean saleItemExists = false;
        for (SaleItem saleItem : sale.getSaleItems())
        {
            if (saleItem.getProduct().getId().equals(productId))
            {
                if (quantity + saleItem.getQuantity() > product.getStock())
                {
                    model.addAttribute("error", "입력한 수량이 상품의 재고를 초과합니다.");
                    List<Product> products = productService.getAllProducts();
                    model.addAttribute("products", products);
                    model.addAttribute("sale", sale);
                    return "sell";
                }
                saleItem.setQuantity(saleItem.getQuantity() + quantity);
                saleService.updateSaleItem(saleItem);
                saleItemExists = true;
                break;
            }
        }

        if (quantity > product.getStock())
        {
            model.addAttribute("error", "입력한 수량이 상품의 재고를 초과합니다.");
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
            model.addAttribute("sale", sale);
            return "sell";
        }

        if (!saleItemExists)
        {
            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(quantity);
            saleService.saveSaleItem(saleItem, sale.getId());

            sale.addSaleItem(saleItem);
        }


        sale.calculateTotalPrice();
        saleService.updateSale(sale);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        model.addAttribute("sale", sale);

        return "sell";
    }


    @GetMapping("/sellConfirm")
    public String sellConfirmPage(HttpSession session, Model model)
    {
        Sale sale = (Sale) session.getAttribute("sale");

        model.addAttribute("sale", sale);

        return "sellConfirm";
    }

    @PostMapping("/sellConfirm")
    public String sellConfirm(HttpSession session)
    {
        Sale sale = (Sale) session.getAttribute("sale");

        if (sale != null)
        {
            for (SaleItem saleItem : sale.getSaleItems())
            {
                Product product = saleItem.getProduct();
                int quantity = saleItem.getQuantity();

                product.setStock(product.getStock() - quantity);
                productService.updateProduct(product);

                saleService.deleteSaleItem(saleItem.getId());

                Log log = new Log();
                log.setAction("sell");
                log.setProduct(product);
                log.setChangeStock(-quantity);
                log.setChangeBalance(product.getPrice() * quantity);

                logService.saveLog(log);
            }

            Balance balance = balanceService.findFirstBalance();
            balance.setAmount(balance.getAmount() + sale.getTotalPrice());
            balanceService.updateBalance(balance);

            saleService.deleteSale(sale.getId());

            session.setAttribute("balance", balance);
        }

        return "sellConfirmSuccess";
    }

    @GetMapping("/deleteAll")
    public String deleteSaleItemFromSellList(@RequestParam("saleId") Long saleId, HttpSession session)
    {
        saleService.deleteAllSaleItems();
        saleService.deleteSale(saleId);

        session.removeAttribute("sale");

        return "redirect:/product/sell";
    }

}
