package study.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class MemberTest {
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void 멤버_팀_테스트() {
        Team team1 = new Team("북산");
        Team team2 = new Team("능남");
        em.persist(team1); em.persist(team2);

        Member member1 = new Member("강백호", 17, team1);
        Member member2 = new Member("서태웅", 17, team1);
        em.persist(member1); em.persist(member2);

        Member member3 = new Member("변덕규", 19 , team2);
        Member member4 = new Member("윤대협", 18 , team2);
        em.persist(member3); em.persist(member4);

        // 초기화
        em.flush();
        em.clear();

        List<Member> findMembers =
                em.createQuery("select m from Member m", Member.class).getResultList();

        for (Member member : findMembers) {
            System.out.println("member : " + member);
            System.out.println("member-Team : " + member.getTeam());
        }
    }
}
