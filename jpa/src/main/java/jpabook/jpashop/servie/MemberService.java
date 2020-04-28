package jpabook.jpashop.servie;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // jpa의 모든 변경은 트랜잭션 안에 있어야 한다. javax꺼도 있지만 스프링이 제공하는걸 쓰는게 좋다. 읽기전용에는 readOnly = true 를 주는게 좋다.
@RequiredArgsConstructor  // final 필드만 주입. AllArg는 final 인붙여도 됨
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional  // readOnly기본이 false라서
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
