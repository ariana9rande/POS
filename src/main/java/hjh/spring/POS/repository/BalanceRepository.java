package hjh.spring.POS.repository;

import hjh.spring.POS.model.Balance;

public interface BalanceRepository
{
    Balance findFirst();

    void update(Balance balance);
}
