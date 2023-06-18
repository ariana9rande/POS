package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Balance;

public interface BalanceRepository
{
    Balance findFirst();

    void update(Balance balance);
}
