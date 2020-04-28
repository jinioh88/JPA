package study.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    public MemberRepository memberRepository;

    @Test
    public void 등록_조회() {
        Member member = new Member("jinioh");

        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember).isEqualTo(member);

        memberRepository.delete(member);

        assertThat(memberRepository.count()).isEqualTo(0);
    }

    @Test
    public void 메서드_이름쿼리() {
        Member member = new Member("jinioh88", 32, null);
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findByNameAndAgeGreaterThan("jinioh88", 30);

        assertThat(findMembers.size()).isEqualTo(1);
    }

    @Test
    public void QueryMethod_test() {
        Member member = new Member("jinioh88", 32, null);
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findByName("jinioh88");

        assertThat(findMembers.size()).isEqualTo(1);
    }
}
