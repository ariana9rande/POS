package hjh.spring.POS.repository;

import hjh.spring.POS.domain.Member;

public interface MemberRepository
{
    void save(Member member);

    Member findByEmail(String email);

    boolean checkDuplicateEmail(String email);
}
