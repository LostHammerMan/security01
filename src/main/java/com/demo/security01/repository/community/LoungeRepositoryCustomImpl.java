package com.demo.security01.repository.community;

import static com.demo.security01.entity.lounge.QLoungeEntity.loungeEntity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.util.StringUtils;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.model.SortOrder;
import com.demo.security01.model.dto.community.LoungeCriteria;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoungeRepositoryCustomImpl implements LoungeRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<LoungeEntity> getAllLoungeWithPaging(Long lastIdx, int pageSize, LoungeCriteria cri) {
        return queryFactory
                .select(loungeEntity)
                .from(loungeEntity)
                .where(ltLoungeIdx(lastIdx),
                		categoryEq(cri.getCateCode()),
                		searchWithTitleAndContents(cri.getKeyword()))
                .orderBy(orderCondition(cri.getOrder()),
                		loungeEntity.idx.desc()
                		)
                .limit(pageSize)
                .fetch();
    }

    @Override
    public Slice<LoungeEntity> getAllLoungeWithPaging2(Long lastIdx, Pageable pageable) {
         List<LoungeEntity> results = queryFactory
                .select(loungeEntity)
                .from(loungeEntity)
                .where(ltLoungeIdx(lastIdx))
                .orderBy(loungeEntity.idx.desc())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return checkAndPage(pageable, results);
    }

    @Override
    public Slice<LoungeEntity> checkAndPage(Pageable pageable, List<LoungeEntity> results) {

        boolean hasNext = false;
        if (results.size() > pageable.getPageSize()){
            hasNext = true;
            results.remove(pageable.getPageSize()); // 한개 더 가져왔으니 더 가져온 데이터 삭제
        }
        return new SliceImpl<>(results, pageable, hasNext);
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
    
    // 카테고리별 검색
    private BooleanExpression categoryEq(Long categoryIdx) {
    	if(categoryIdx == null) return null;
    	return loungeEntity.cateCode.categoryIdx.eq(categoryIdx);
    }
    
    // 제목 + 내용 검색
    private BooleanBuilder searchWithTitleAndContents(String keyword) {
    	BooleanBuilder builder = new BooleanBuilder();
    	return builder
    			.or(titleLike(keyword))
    			.or(contentsLike(keyword));
    }
    
    // 제목 검색
    private BooleanExpression titleLike(String keyword) {
    	if(!StringUtils.hasText(keyword)) return null;
    	return loungeEntity.title.contains(keyword);
    }
    
    // 내용 검색
    private BooleanExpression contentsLike(String keyword) {
    	if(!StringUtils.hasText(keyword)) return null;
    	return loungeEntity.content.contains(keyword);
    }
    
    // booleanExpression => where 절의 조건
    // OrderSpecifier => 정렬 조건
    
    // 최신순, 많이 본 순, 좋아요 순
    private OrderSpecifier<?> orderCondition(SortOrder order) {
    	if(order == null) return null;
    	
    	if(order == SortOrder.RECENT) {
    		return new OrderSpecifier<>(Order.DESC, loungeEntity.regDate);
    	}else if (order == SortOrder.VIEW) {
			return new OrderSpecifier<>(Order.DESC, loungeEntity.viewCount);
		}else if(order == SortOrder.LIKE) {
			return new OrderSpecifier<>(Order.DESC, loungeEntity.likeCount);
		}
    	return null;
    }
}
