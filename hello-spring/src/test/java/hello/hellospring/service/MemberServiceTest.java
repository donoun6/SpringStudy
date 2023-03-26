package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService; //회원가입을 하기위해선 서비스가 필요하다.
    MemoryMemberRepository memberRepository; //clear 하기 위해 필요하다.

    @BeforeEach
    public void beforeEach(){ //같은 메모리멤버레포지토리를 사용하기 위해
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){ //메모리 초기화
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given : 해당 데이터를 기반
        Member member = new Member();
        member.setName("spring");

        //when : 해당 로직을 검증
        Long saveId = memberService.join(member);

        //then : 검증부
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //중복값을 넣었을때 해당 예외가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        
        //then
        //메세지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}