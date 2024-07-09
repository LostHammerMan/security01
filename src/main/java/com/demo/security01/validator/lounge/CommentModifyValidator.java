package com.demo.security01.validator.lounge;

import com.demo.security01.model.dto.comment.request.CommentModifyRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component(value = "commentModifyValidator")
public class CommentModifyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info("======= CommentModifyValidator - support =======");
        return CommentModifyRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CommentModifyRequestDto request = (CommentModifyRequestDto) target;
        log.info("\t\tCommentModifyValidator - validate");
        // null 체크
        if (!StringUtils.hasText(request.getContent())){
            errors.rejectValue("content", "NotBlank", "댓글을 입력해주세요");
            return;
        }

        // 문자 길이 제한
        if (request.getContent().length() < 1 || request.getContent().length() > 255){
            errors.rejectValue("content", "MinMaxLength", "댓글은 255 글자를 초과할 수 없습니다");
            return;
        }
    }

    //
}
