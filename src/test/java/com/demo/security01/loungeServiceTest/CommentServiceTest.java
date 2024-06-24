package com.demo.security01.loungeServiceTest;

import com.demo.security01.repository.comment.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CommentServiceTest {

    @Autowired
    CommentRepository repository;

    @Test
    public void commentCount(){
        Integer commentCount1 = repository.getCommentListCount(326L);
        Integer commentCount2 = repository.getCommentListCount(325L);

        log.info("commentCount1 = {}", commentCount1);
        log.info("commentCount2 = {}", commentCount2);

        Assertions.assertEquals(7, commentCount1);
        Assertions.assertEquals(2, commentCount2);
    }
}
