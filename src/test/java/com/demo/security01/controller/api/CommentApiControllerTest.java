package com.demo.security01.controller.api;

import com.demo.security01.entity.comment.CommentEntity;
import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import com.demo.security01.model.dto.comment.request.CommentRequestDto;
import com.demo.security01.model.dto.comment.response.CommentResponseDto;
import com.demo.security01.service.community.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CommentApiControllerTest {

    @Autowired
    private CommentService commentService;




}