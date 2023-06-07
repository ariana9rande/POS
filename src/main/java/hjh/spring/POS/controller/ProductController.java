package hjh.spring.POS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController
{
    @GetMapping("/product/register")
    public String productManage()
    {
        return "productManage";
    }
}
