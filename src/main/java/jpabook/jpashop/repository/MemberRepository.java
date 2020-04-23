package jpabook.jpashop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
//    @PersistenceContext // jpa 관리
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 올림. 키가 id 값이 됨.
        // GenerateValue 전략에서는 persist해도 insert가 안날아간다. transaction이 commit이 될때 fllush 되면서 insert가 날아간다.
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // jpql: from 의 대상이 테이블이 아닌 객체
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
