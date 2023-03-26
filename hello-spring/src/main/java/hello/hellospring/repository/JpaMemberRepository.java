package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //jpa가 inset 쿼리 만들고 db에 넣고 setId까지 해준다.
        em.persist(member); //persist : 영속하다, 영구저장하다 라는 뜻을 가졌다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //조회할 타입과 식별자(PK값)를 넣어주면 조회가 가능하다.
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    //저장 조회 업데이트는 sql을 짤 필요가 없다.
    //아래의 경우 PK기반이 아니 나머지들은 JPQL을 작성해준다.

    @Override
    public Optional<Member> findByName(String name) {
        //jpql 쿼리 , 객체를 대상으로 쿼리를 날린다. 그럼 이게 sql로 번역이 된다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //Member 엔티티를 조회하고 Member엔티티(객체) 자체를 셀렉한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
