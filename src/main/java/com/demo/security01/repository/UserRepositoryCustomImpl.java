package com.demo.security01.repository;

import com.demo.security01.entity.User;
import com.demo.security01.model.dto.AdminUpdateDto;
import com.demo.security01.model.dto.DeleteDto;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.demo.security01.entity.QUser.*;


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
                .selectFrom(user)
                .fetch();
        System.out.println("allUser = " + allUser);
        return allUser;

    }

    @Override
    public User findUser(int id) {
        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    @Transactional
    @Override
    public void updateUser(AdminUpdateDto dto) {
        long count = queryFactory
                .update(user)
                .set(user.role, dto.getRole())
                .where(user.id.eq(dto.getId()))
                .execute();

    }

    @Transactional
    @Override
    public void delete(int id) {
        queryFactory
                .delete(user)
                .where(user.id.eq(id))
                .execute();
    }

    @Transactional
    @Override
    public void deleteAll(List<Integer> ids) {
//        List<Integer> ids = new ArrayList<>(id);
        queryFactory
                .delete(user)
                .where(user.id.in(ids))
                .execute();
    }
}
