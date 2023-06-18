package hjh.spring.POS.repository;

import hjh.spring.POS.model.Member;

public interface MemberRepository
{
    void save(Member member);

    Member findByEmail(String email);

    Member findByEmailAndPassword(String email, String password);

    boolean checkDuplicateEmail(String email);
}
