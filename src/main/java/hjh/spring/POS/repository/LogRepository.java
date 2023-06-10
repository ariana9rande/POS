package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LogRepository
{
    void saveLog(Log log);

    List<Log> getLogs(String action, String range);

    Map<String, List<Log>> groupLogsByAction(List<Log> logs);

    Map<String, Map<String, Integer>> calculateStatistics(Map<String, List<Log>> groupedLogs);
}