package hjh.spring.POS.configuration;

import hjh.spring.POS.repository.*;
import hjh.spring.POS.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "hjh.spring.POS")
public class JavaConfig
{
    @Bean
    public MemberRepository memberRepository()
    {
        return new MemberRepositoryImpl();
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository)
    {
        return new MemberService(memberRepository);
    }

    @Bean
    public ProductRepository productRepository()
    {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository)
    {
        return new ProductService(productRepository);
    }

    @Bean
    public SaleRepository saleRepository(ProductService productService)
    {
        return new SaleRepositoryImpl(productService);
    }

    @Bean
    public SaleService saleService(SaleRepository saleRepository)
    {
        return new SaleService(saleRepository);
    }

    @Bean
    public BalanceRepository balanceRepository()
    {
        return new BalanceRepositoryImpl();
    }

    @Bean
    public BalanceService balanceService(BalanceRepository balanceRepository)
    {
        return new BalanceService(balanceRepository);
    }

    @Bean
    public LogRepository logRepository(ProductService productService)
    {
        return new LogRepositoryImpl(productService);
    }

    @Bean
    public LogService logService(LogRepository logRepository)
    {
        return new LogService(logRepository);
    }
}
