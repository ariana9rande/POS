package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Product;
import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;
import hjh.spring.POS.service.ProductService;
import hjh.spring.POS.service.SaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController
{
    private final ProductService productService;
    private final SaleService saleService;

    public ProductController(ProductService productService, SaleService saleService)
    {
        this.productService = productService;
        this.saleService = saleService;
    }

    @GetMapping("/register")
    public String productManage()
    {
        return "productRegister";
    }

    @PostMapping("/register")
    public String registerProduct(
            @RequestParam("name") String name,
            @RequestParam("price") int price,
            @RequestParam("stock") int stock,
            Model model
    )
    {
        // Product 객체 생성
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        // ProductService를 통해 상품 등록
        productService.registerProduct(product);
        model.addAttribute("product", product);

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
                             Model model)
    {
        productService.addProductStock(productId, quantity);
        Product product = productService.findProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("quantity", quantity);
        return "productAddSuccess";
    }

    @GetMapping("/sell")
    public String productSellForm(Model model, HttpSession session)
    {
        // Sale 객체 생성
        Sale sale = (Sale) session.getAttribute("sale");
        if(sale == null)
        {
            sale = new Sale();
            saleService.saveSale(sale);
        }

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // Sale 객체를 세션에 저장
        session.setAttribute("sale", sale);

        return "sell";
    }


    @PostMapping("/addToSellList")
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
            session.setAttribute("sale", sale);
        }

        // 상품 정보 가져오기
        Product product = productService.findProductById(productId);

        boolean saleItemExists = false;
        for (SaleItem saleItem : sale.getSaleItems())
        {
            if (saleItem.getProduct().getId().equals(productId))
            {
                if(quantity + saleItem.getQuantity() > product.getStock())
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

        if (quantity > product.getStock()) {
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
    public String sellConfirm(HttpSession session, Model model)
    {
        Sale sale = (Sale) session.getAttribute("sale");

        model.addAttribute("sale", sale);

        return "sellConfirm";
    }
}
