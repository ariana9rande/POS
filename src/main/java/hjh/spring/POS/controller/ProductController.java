package hjh.spring.POS.controller;

import hjh.spring.POS.domain.*;
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
    public String productManage(Model model)
    {
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

        // Product 객체 생성
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        product.setPurchasePrice((int)(price * 0.3));

        // ProductService를 통해 상품 등록
        productService.registerProduct(product);
        model.addAttribute("product", product);

        Balance balance = balanceService.findFirstBalance();
        balance.setAmount(balance.getAmount() - product.getPurchasePrice() * stock);
        balanceService.updateBalance(balance);

        // Log 객체 생성 및 설정
        Log log = new Log();
        log.setAction("register");
        log.setProduct(product);
        log.setChangeStock(stock);
        log.setChangeBalance(-(product.getPurchasePrice() * stock));

        logService.saveLog(log);

        // 세션에 balance 전달
        session.setAttribute("balance", balance);

        // 등록 후 리다이렉트할 경로 반환 (예: 메인 페이지로 이동)
        return "productRegisterSuccess";
    }

    @GetMapping("/products")
    public String products(Model model)
    {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/add")
    public String productAddForm(Model model)
    {
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

        // Log 객체 생성 및 설정
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
//        // Sale 객체 생성
//        Sale sale = (Sale) session.getAttribute("sale");
//        if (sale == null)
//        {
//            sale = new Sale();
//            saleService.saveSale(sale);
//        }

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // Sale 객체를 세션에 저장
//        session.setAttribute("sale", sale);

        return "sell";
    }


    @PostMapping("/sell")
    public String addToSellList(@RequestParam("productId") Long productId,
                                @RequestParam("quantity") int quantity,
                                HttpSession session,
                                Model model)
    {

        // 세션에서 Sale 객체 가져오기
        Sale sale = (Sale) session.getAttribute("sale");

        // Sale 객체가 없을 경우 새로 생성
        if (sale == null)
        {
            sale = new Sale();
            saleService.saveSale(sale);
            session.setAttribute("sale", sale);
        }

        // 상품 정보 가져오기
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
                    // 모델에 Sale 객체 추가
                    model.addAttribute("sale", sale);
                    return "sell";
                }
                // 이미 같은 상품을 가지는 SaleItem이 있을 경우 수량 업데이트
                saleItem.setQuantity(saleItem.getQuantity() + quantity);
                saleService.updateSaleItem(saleItem);
                saleItemExists = true;
                break;
            }
        }

        if (quantity > product.getStock())
        {
            // 재고(stock)를 초과하는 수량이 입력된 경우, 에러 메시지를 모델에 추가하고 다시 sell 페이지로 이동
            model.addAttribute("error", "입력한 수량이 상품의 재고를 초과합니다.");
            // 모델에 상품 목록 추가
            List<Product> products = productService.getAllProducts();
            model.addAttribute("products", products);
            // 모델에 Sale 객체 추가
            model.addAttribute("sale", sale);
            return "sell";
        }

        // SaleItem이 없을 경우 새로 생성
        if (!saleItemExists)
        {
            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(quantity);
            saleService.saveSaleItem(saleItem, sale.getId());

            // Sale 객체에 SaleItem 추가
            sale.addSaleItem(saleItem);
        }


        sale.calculateTotalPrice();
        saleService.updateSale(sale);

        // 모델에 상품 목록 추가
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // 모델에 Sale 객체 추가
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
    public String sellConfirm(HttpSession session, Model model)
    {
        // 세션에서 Sale 객체 가져오기
        Sale sale = (Sale) session.getAttribute("sale");

        // Sale 객체가 null이 아니라면 SaleItem을 판매 처리하고 Product의 stock을 업데이트
        if (sale != null)
        {
            for (SaleItem saleItem : sale.getSaleItems())
            {
                Product product = saleItem.getProduct();
                int quantity = saleItem.getQuantity();

                // Product의 stock 업데이트
                product.setStock(product.getStock() - quantity);
                productService.updateProduct(product);

                // SaleItem 삭제
                saleService.deleteSaleItem(saleItem);

                // Log 객체 생성 및 설정
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

            // Sale 객체 삭제
            saleService.deleteSale(sale.getId());

            session.setAttribute("balance", balance);
        }

        return "sellConfirmSuccess";
    }

}
