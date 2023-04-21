package com.demo.security01.repository;

import com.demo.security01.entity.QUser;
import com.demo.security01.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

   /* public UserRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }*/


    @Override
    public List<User> findAllUser() {
        List<User> allUser = queryFactory
                .selectFrom(QUser.user)
                .fetch();
        System.out.println("allUser = " + allUser);
        return allUser;
    }

    @Override
    public User findUser(int id) {
        return queryFactory
                .selectFrom(QUser.user)
                .where(QUser.user.id.eq(id))
                .fetchOne();
    }
}
