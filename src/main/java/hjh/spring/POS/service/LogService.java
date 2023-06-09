package hjh.spring.POS.service;

import hjh.spring.POS.domain.Log;
import hjh.spring.POS.repository.LogRepository;

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
}
