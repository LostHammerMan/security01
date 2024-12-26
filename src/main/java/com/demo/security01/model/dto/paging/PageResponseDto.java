package com.demo.security01.model.dto.paging;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Pageable;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDto<E> {

	private final Pageable pageable;
	
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private int totalCount, prevPage, nextPage, realEnd, current, end;
	private boolean prev, next;

	@Builder
	public PageResponseDto(Pageable pageable, List<E> dtoList, long totalCount) {
		this.pageable = pageable;
		this.dtoList = dtoList;
		this.totalCount = (int) totalCount;
//		this.current = pageable.getPageNumber();
		this.current = Math.max(pageable.getPageNumber() + 1, 1);
		
		// 보여줄 끝 번호
//		int end = (int) (Math.ceil(pageable.getPageNumber() /5.0)) * 5;
		end = (int) (Math.ceil((current) /5.0)) * 5;
		
		// 실제 마지막 페이지
		this.realEnd = (int)(Math.ceil(totalCount) / (double)pageable.getPageSize());
		
		// end가 realEnd 를 넘지 않도록
//		end = end > realEnd ? realEnd : end;
		this.end = Math.min(end, realEnd);
		
		// 시작 번호
//		int start = Math.max(end - 4, 1);
		int start = Math.max(((current -1) / 5) * 5 + 1, 1);
	
		// next, prev
		this.prev = start > 1;
		
		// next 는 end < realEnd 에만 true
//		this.next = totalCount > end * pageable.getPageSize();
		this.next = (end == realEnd) ? false : true;
//		this.next = end < realEnd;
		
		
		// 페이지 각 번호
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		
		// prevPage, nextPage
//		this.prevPage = prev ? end - 5 : 0;
		this.prevPage = prev ? end - 6 : 0;
		this.nextPage = next ? end + 1 : 0;
		
		// 페이지가 1개 뿐인 경우
		// Collections.singletonList(1) : 1개의 요소(1) 만을 포함하는 불변 리스트 생성
		if(totalCount == 1 && pageNumList.isEmpty()) {
			pageNumList = Collections.singletonList(1);
		}
		
	}

	
}
