package jpabook.jpashop;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

//    @Autowired
//    MemberRepository memberRepository;
//
//    // 엔티티 매니저를 통한 모든 데이터 변경은 트랜잭션 안에서 이뤄져야 한다.
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() throws Exception {
//        Member member = new Member();
//        member.setUsername("memberA");
//
//        Long saveId = memberRepository.save(member);
//        Member findMember = memberRepository.find(saveId);
//
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member); // 같은 영속성 컨텍스트엣 비교할거니 id가 같으면 같은 엔티티로 본다.
//    }
}