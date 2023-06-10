package hjh.spring.POS.controller;

import hjh.spring.POS.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class LogController
{
    private final LogService logService;

    public LogController(LogService logService)
    {
        this.logService = logService;
    }

    @GetMapping("/")
    public String StatisticsPage()
    {
        return "statistics";
    }
}
