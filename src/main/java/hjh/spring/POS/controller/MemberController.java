package hjh.spring.POS.controller;

import hjh.spring.POS.domain.Member;
import hjh.spring.POS.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController
{
    private final MemberService memberService;

    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String signupForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "member/join";
    }

    @PostMapping("/join")
    public String joinMember(Member member, Model model)
    {
        boolean isDuplicateEmail = memberService.checkDuplicateEmail(member.getEmail());
        if (isDuplicateEmail)
        {
            String message = "중복된 이메일입니다.";
            model.addAttribute("duplicateEmail", message);
            return "member/join";
        }
        else
        {
            memberService.register(member);
            model.addAttribute("member", member);
            return "member/joinSuccess";
        }
    }

    @GetMapping("/login")
    public String loginForm(Model model)
    {
        model.addAttribute("member", new Member());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session)
    {
        Member member = memberService.login(email, password);
        if (member == null)
        {
            model.addAttribute("loginFailed", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
        }

        session.setAttribute("loginMember", member);
        return "redirect:/";
    }
}
