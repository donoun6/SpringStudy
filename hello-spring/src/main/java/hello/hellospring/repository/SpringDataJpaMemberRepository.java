package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스가 인터페이스를 받을때는 extends를 쓴다. 인터페이스는 다중상속 가능
//인터페이스로 JpaRepository 받고있으면 스프링 데이터 jap가 구현체를 자동으로 만들어주고 스프링빈을 자동으로 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
