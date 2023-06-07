package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Product;
import hjh.spring.POS.service.ProductService;
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

    public ProductController(ProductService productService)
    {
        this.productService = productService;
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
}
