package com.demo.security01.repository.community;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.lounge.QLoungeEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.demo.security01.entity.lounge.QLoungeEntity.loungeEntity;

@RequiredArgsConstructor
public class LoungeRepositoryCustomImpl implements LoungeRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<LoungeEntity> getAllLoungeWithPaging(Long lastIdx, int pageSize) {
        return queryFactory
                .select(loungeEntity)
                .from(loungeEntity)
                .where(ltLoungeIdx(lastIdx))
                .orderBy(loungeEntity.idx.desc())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public Long getMaxLoungeIdx() {
        return queryFactory.select(loungeEntity.idx.max())
                .from(loungeEntity)
                .fetchFirst();
    }

    private BooleanExpression ltLoungeIdx(Long loungeId){

        // 1. id < 파라미터를 첫 페이지에선 사용하지 않기 위한 동적 쿼리
        // BooleanExpression 자리에 null 이 반환되면 조건문에서 자동으로 제거됨
        if (loungeId == null){
            return null;
        }
        return loungeEntity.idx.lt(loungeId);
    }
}
