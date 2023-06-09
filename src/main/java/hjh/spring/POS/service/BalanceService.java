package hjh.spring.POS.service;

import hjh.spring.POS.domain.Balance;
import hjh.spring.POS.repository.BalanceRepository;

public class BalanceService
{
    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository)
    {
        this.balanceRepository = balanceRepository;
    }

    public Balance findBalanceById(Long balanceId)
    {
        return balanceRepository.findBalanceById(balanceId);
    }

    public Balance findFirstBalance()
    {
        return balanceRepository.findFirst();
    }

    public void updateBalance(Balance balance)
    {
        balanceRepository.update(balance);
    }
}
