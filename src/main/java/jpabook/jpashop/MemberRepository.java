package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // component scan 의 대상, 자동으로 Spring bean에 적용됨
public class MemberRepository {

    @PersistenceContext // Spring Container가 EntityManager를 자동 주입
    private EntityManager em; // em을 통한 모든 data변경은 transaction 내에서 이루어져야함.

    public Long save(Member member){
        em.persist(member); // Command와 query 분리
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

}
