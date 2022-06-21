package spring_study.spring_study;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_study.spring_study.repository.JdbcMemberRepository;
import spring_study.spring_study.repository.JpaMemberRepository;
import spring_study.spring_study.repository.MemberRepository;
import spring_study.spring_study.repository.MemoryMemberRepository;
import spring_study.spring_study.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
