package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Product;
import hjh.spring.POS.domain.Sale;
import hjh.spring.POS.domain.SaleItem;
import hjh.spring.POS.service.ProductService;
import hjh.spring.POS.service.SaleService;
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
    public String productSellForm(Model model)
    {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "sell";
    }

    @PostMapping("/addToSellList")
    public String addToSellList(@RequestParam("productId") Long productId,
                                @RequestParam("quantity") int quantity,
                                Model model)
    {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        Product product = productService.findProductById(productId);
        SaleItem saleItem = new SaleItem();
        saleItem.setProduct(product);
        saleItem.setQuantity(quantity);

        Sale sale = saleService.getCurrentSale();
        System.out.println("sale = " + sale);
        if(sale == null)
        {
            sale = new Sale();
            saleService.createSale(sale);
        }
        System.out.println("sale = " + sale);
        saleItem.setSale(sale);

        sale.addSaleItem(saleItem);
        saleService.saveSale();
        saleService.setSaleTotalPrice(sale.getId());

        model.addAttribute("sale", sale);

        return "sell";
    }

    @GetMapping("/product/sellConfirm")
    public String sellConfirm(Model model)
    {
        Sale sale = saleService.getCurrentSale();

        model.addAttribute("sale", sale);

        return "sellConfirm";
    }
}
