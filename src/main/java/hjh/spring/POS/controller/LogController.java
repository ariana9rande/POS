package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Log;
import hjh.spring.POS.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return "statistics/statistics";
    }

    @GetMapping("/daily")
    public String dailyStatisticsPage(
                                      Model model)
    {

        return "statistics/daily";
    }
}
