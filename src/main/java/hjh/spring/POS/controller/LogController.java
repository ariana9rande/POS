package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Log;
import hjh.spring.POS.domain.Member;
import hjh.spring.POS.domain.Product;
import hjh.spring.POS.service.LogService;
import hjh.spring.POS.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/statistics")
public class LogController
{
    private final LogService logService;
    private final ProductService productService;

    public LogController(LogService logService, ProductService productService)
    {
        this.logService = logService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String StatisticsForm(HttpSession session, Model model)
    {
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember == null || !loginMember.getRole().equals("매니저"))
        {
            model.addAttribute("errorMessage", "권한이 없습니다.");
            return "/authError";
        }
        return "/statistics/statisticsForm";
    }

    @GetMapping("/statistics")
    public String Statistics(@RequestParam("action") String action,
                             @RequestParam("range") String range,
                             Model model)
    {
        List<Product> products = productService.getAllProducts();

        List<Log> logs = logService.getLogs(action, range);

        Map<String, List<Log>> groupedLogs = logService.getGroupLogsByAction(logs);

        Map<String, Map<String, Integer>> statistics = logService.getCalculatedLogs(groupedLogs);

        model.addAttribute("logs", logs);
        model.addAttribute("action", action);
        model.addAttribute("range", range);
        model.addAttribute("groupedLogs", groupedLogs);
        model.addAttribute("statistics", statistics);
        model.addAttribute("products", products);

        return "/statistics/statistics";
    }


}
