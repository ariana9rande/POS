package hjh.spring.POS.service;

import hjh.spring.POS.domain.Member;
import hjh.spring.POS.repository.MemberRepository;

public class MemberService
{
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    public void register(Member member)
    {
        memberRepository.save(member);
    }

    public boolean login(String email, String password)
    {
        Member member = memberRepository.findByEmail(email);
        return member != null && member.getPassword().equals(password);
    }

    public boolean checkDuplicateEmail(String email)
    {
        return memberRepository.checkDuplicateEmail(email);
    }
}
