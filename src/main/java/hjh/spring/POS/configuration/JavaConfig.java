package hjh.spring.POS.configuration;

import hjh.spring.POS.controller.MemberController;
import hjh.spring.POS.repository.MemberRepository;
import hjh.spring.POS.repository.MemberRepositoryImpl;
import hjh.spring.POS.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

    // 다른 의존성을 등록하는 코드 작성
    // ...
}
