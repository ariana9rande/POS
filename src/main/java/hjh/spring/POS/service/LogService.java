package hjh.spring.POS.service;

import hjh.spring.POS.domain.Log;
import hjh.spring.POS.repository.LogRepository;

import java.util.*;

public class LogService
{
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository)
    {
        this.logRepository = logRepository;
    }

    public void saveLog(Log log)
    {
        logRepository.saveLog(log);
    }

    public List<Log> getLogs(String action, String range)
    {
        return logRepository.getLogs(action, range);
    }

    public Map<String, List<Log>> getGroupLogsByAction(List<Log> logs)
    {
        return logRepository.groupLogsByAction(logs);
    }

    public Map<String, Map<String, Integer>> getCalculatedLogs(Map<String, List<Log>> groupedLogs)
    {
        return logRepository.calculateLogs(groupedLogs);
    }
}
