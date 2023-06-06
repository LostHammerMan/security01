package com.demo.security01.validator;

import com.demo.security01.model.dto.paging.Criteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.net.BindException;

@Slf4j
@Component(value = "userListValidator")
//public class UserListValidator implements Validator {
public class UserListValidator {

   /* @Override
    public boolean supports(Class<?> clazz) {
        return Criteria.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Criteria cri = (Criteria) target;
        // 타입 체크

    }

    private void checkType(int perPageNum){
        Criteria cri = new Criteria();

//        if (cri.getPerPageNum() instanceof NumberFormatException){

        }

    }
*/

}
