package com.demo.security01.repository.community;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
public class LoungeRepositoryCustomImpl implements LoungeRepositoryCustom{

    private final JPAQueryFactory factory;
}
