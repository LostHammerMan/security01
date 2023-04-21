package com.demo.security01;

import com.demo.security01.entity.QUser;
import com.demo.security01.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class QueryDslTest {

    @Autowired
    EntityManager em;

    @Test
    void contextLoad(){
        User user = new User();
        em.persist(user);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QUser qUser = QUser.user;

        User result = query
                .selectFrom(qUser)
                .fetchOne();

        // Q타입 동작 확인
        Assertions.assertThat(result).isEqualTo(user);

        // 롬복 동작 확인(user.getId)
        Assertions.assertThat(result.getId()).isEqualTo(user.getId());
    }
}
