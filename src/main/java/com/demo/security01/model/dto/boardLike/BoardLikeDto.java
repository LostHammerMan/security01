package com.demo.security01.model.dto.boardLike;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardLikeDto {

    private boolean isCheck = false;
    private long likeId;
    private String userName;
    private Long boardId;

}
