package study.datajpa;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.UserDTO;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.TeamRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {
    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public TeamRepository teamRepository;

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
    public void queryMethod_test() {
        Member member = new Member("jinioh88", 32, null);
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findByName("jinioh88");

        assertThat(findMembers.size()).isEqualTo(1);
    }

    @Test
    public void nameedQuery_test() {
        Member member = new Member("jinioh88", 32, null);
        memberRepository.save(member);

        List<Member> findMembers = memberRepository.findMember("jinioh88", 30);

        assertThat(findMembers.size()).isEqualTo(1);
    }

    @Test
    public void finduserName_test() {
        Member member = new Member("jinioh88", 32, null);
        memberRepository.save(member);

        List<String> findMembers = memberRepository.findUsername();

        assertThat(findMembers.get(0)).isEqualTo("jinioh88");
    }

    @Test
    public void getDTO_test() {
        Team team = new Team("team1");
        teamRepository.save(team);

        Member member = new Member("jinioh88", 32, team);
        memberRepository.save(member);

        List<UserDTO> findMembers = memberRepository.findUserDTO();

        assertThat(findMembers.get(0).getTeamName()).isEqualTo(team.getName());
    }

    @Test
    public void findByUserNames() {
        Team team = new Team("team1");
        teamRepository.save(team);

        Member member = new Member("jinioh88", 32, team);
        memberRepository.save(member);

        Member member2 = new Member("jinioh22", 28, team);
        memberRepository.save(member2);

        List<Member> findMembers = memberRepository.findByUserNames(Lists.newArrayList("jinioh88", "jinioh22"));

        assertThat(findMembers.size()).isEqualTo(2);
    }

    @Test
    public void test_page() {
        memberRepository.save(new Member("a", 20));
        memberRepository.save(new Member("b", 20));
        memberRepository.save(new Member("c", 20));
        memberRepository.save(new Member("d", 20));
        memberRepository.save(new Member("e", 20));

        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<Member> members = memberRepository.findByAge(20, pageRequest);

        assertThat(members.getTotalElements()).isEqualTo(5);
        assertThat(members.getContent().size()).isEqualTo(3);
        assertThat(members.getTotalPages()).isEqualTo(2);
        assertThat(members.isFirst()).isTrue();
        assertThat(members.isLast()).isFalse();
    }

    @Test
    public void test_bulk() {
        memberRepository.save(new Member("a", 19));
        memberRepository.save(new Member("b", 18));
        memberRepository.save(new Member("c", 20));
        memberRepository.save(new Member("d", 21));
        memberRepository.save(new Member("e", 30));
        int expectedSize = 3;

        int result = memberRepository.bulkAgePlus(20);

        assertThat(result).isEqualTo(expectedSize);
    }
}
