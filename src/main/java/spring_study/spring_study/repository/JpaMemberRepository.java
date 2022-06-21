package spring_study.spring_study.repository;

import spring_study.spring_study.domain.Member;

import javax.persistence.EntityManager;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //JPA는 EntityManger로 대부분 동작
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}
