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
//    @Bean
//    public MemberController memberController(MemberService memberService)
//    {
//        // MemberController의 생성자를 호출하여 MemberService를 주입하는 코드 작성
//        return new MemberController(memberService);
//    }

    @Bean
    public MemberRepository memberRepository()
    {
        // MemberRepository의 구현체를 생성하여 반환하는 코드 작성
        return new MemberRepositoryImpl();
    }

    @Bean
    public MemberService memberService(MemberRepository memberRepository)
    {
        // MemberService의 생성자를 호출하여 MemberRepository를 주입하는 코드 작성
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

//    @Bean
//    public ProductController productController(ProductService productService)
//    {
//        return new ProductController(productService);
//    }

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
    public LogRepository logRepository()
    {
        return new LogRepositoryImpl();
    }

    @Bean
    public LogService logService(LogRepository logRepository)
    {
        return new LogService(logRepository);
    }
}
