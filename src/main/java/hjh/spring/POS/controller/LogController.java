package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Log;
import hjh.spring.POS.service.LogService;
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

    public LogController(LogService logService)
    {
        this.logService = logService;
    }

    @GetMapping("/")
    public String StatisticsForm()
    {
        return "/statistics/statisticsForm";
    }

    @GetMapping("/statistics")
    public String Statistics(@RequestParam("action") String action,
                             @RequestParam("range") String range,
                             Model model)
    {
        // 로그 가져오기
        List<Log> logs = logService.getLogs(action, range);

        // 액션별로 로그 그룹화
        Map<String, List<Log>> groupedLogs = logService.groupLogsByAction(logs);

        // 액션별 통계 계산
        Map<String, Map<String, Integer>> statistics = logService.calculateStatistics(groupedLogs);

        // 모델에 데이터 추가
        model.addAttribute("logs", logs);
        model.addAttribute("action", action);
        model.addAttribute("range", range);
        model.addAttribute("groupedLogs", groupedLogs);
        model.addAttribute("statistics", statistics);

        return "/statistics/statistics";
    }
}
