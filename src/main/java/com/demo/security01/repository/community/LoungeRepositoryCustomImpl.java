package com.demo.security01.repository.community;

import static com.demo.security01.entity.QCategoryEntity.categoryEntity;
import static com.demo.security01.entity.lounge.QBoardLike.boardLike;
import static com.demo.security01.entity.lounge.QLoungeEntity.loungeEntity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.util.StringUtils;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import com.demo.security01.model.SortOrder;
import com.demo.security01.model.dto.community.LoungeCriteria;
import com.demo.security01.model.dto.community.LoungeListResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoungeRepositoryCustomImpl implements LoungeRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public List<LoungeEntity> getAllLoungeWithPaging(Long lastIdx, int pageSize, LoungeCriteria cri, User user) {
        return queryFactory
                .selectDistinct(loungeEntity)
                .from(loungeEntity)
                .leftJoin(boardLike).on(boardLike.lounge.eq(loungeEntity))
                .where(ltLoungeIdx(lastIdx),
                		categoryEq(cri.getCateCode()),
                		searchWithTitleAndContents(cri.getKeyword()),
                		checkLoungeLike(user)
                		)
                .orderBy(orderCondition(cri.getOrder()),
                		new OrderSpecifier<>(Order.DESC, loungeEntity.idx))
//                .orderBy(orderCondition(cri.getOrder()), 
//                		loungeEntity.idx.desc()
//                		)
                .limit(pageSize)
                .fetch();
    }
    
    @Override
	public List<LoungeListResponseDto> getLoungeTop4() {
		// TODO Auto-generated method stub
		return queryFactory.select(Projections.fields(LoungeListResponseDto.class, 
				loungeEntity.idx,
				loungeEntity.title,
				loungeEntity.regDate,
				loungeEntity.viewCount,
				categoryEntity.categoryName
				))
				.from(loungeEntity)
				.join(loungeEntity.cateCode, categoryEntity)
				.orderBy(loungeEntity.viewCount.desc())
				.limit(4)
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
    
    // 좋아요 보기 체크
    private BooleanExpression checkLoungeLike(User loginUser) {
    	if(loginUser == null) return null;
    	return boardLike.user.eq(loginUser);
    }
    
    // booleanExpression => where 절의 조건
    // OrderSpecifier => 정렬 조건
    
    // 최신순, 많이 본 순, 좋아요 순
    private OrderSpecifier<?> orderCondition(SortOrder order) {
    	
    	if(order == null) {
    		return new OrderSpecifier<>(Order.DESC, loungeEntity.regDate);
    	}
    	
    	switch (order) {
		case RECENT:
			return new OrderSpecifier<>(Order.DESC, loungeEntity.regDate);

		case VIEW:
			return new OrderSpecifier<>(Order.DESC, loungeEntity.viewCount); 
			
		case LIKE:
			return new OrderSpecifier<>(Order.DESC, loungeEntity.likeCount);
			
		default:
			return new OrderSpecifier<>(Order.DESC, loungeEntity.regDate);
		}
    	
    	/* order 가 null 인 경우 처리  
    	 * 1. 기본 정렬값 설정
    	 * 
    	 * 2. OrderSpecifier 배열로 처리
    	 *  - 빈 배열을 전달하면 오류 발생 X, 정렬 조건이 없을 때 빈 배열을 넘길 수 있음
    	 * 
    	 * */
//    	if(order == null) {
//    		return new OrderSpecifier<>(Order.DESC, loungeEntity.regDate);
//    	}
    	
		/*
		 * if(order == SortOrder.RECENT) { return new OrderSpecifier<>(Order.DESC,
		 * loungeEntity.regDate); }else if (order == SortOrder.VIEW) { return new
		 * OrderSpecifier<>(Order.DESC, loungeEntity.viewCount); }else if(order ==
		 * SortOrder.LIKE) { return new OrderSpecifier<>(Order.DESC,
		 * loungeEntity.likeCount); } return new OrderSpecifier<>(Order.DESC,
		 * loungeEntity.regDate);
		 */
    }



	
    
    
}
