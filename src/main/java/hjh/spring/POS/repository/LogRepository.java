package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Log;

import java.util.ArrayList;
import java.util.List;

public interface LogRepository
{
    void saveLog(Log log);

    List<Log> getLogs(String action, String range);

}