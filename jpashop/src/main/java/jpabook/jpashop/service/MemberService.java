package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// 읽기만 하는 경우 readOnly 권장
// springframework의 @Transactional 사용 권장
@RequiredArgsConstructor // injection 방법 중 권장
public class MemberService {

//    @Autowired // field injection
//    private MemberRepository memberRepository;
//
//    @Autowired // setter injection
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    private final MemberRepository memberRepository;

//    @Autowired // 생성자 injection (생성자 하나일 경우 Autowired 생략 가능)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 회원 가입
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
