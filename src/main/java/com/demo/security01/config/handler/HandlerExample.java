package com.demo.security01.config.handler;

import com.demo.security01.model.dto.reponseDto.ResponseEntityDto;
import com.demo.security01.model.dto.error.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

// 400 Bad Request      잘못된 문법
// 401 Unauthorized     비인증
// 403 Forbidden        접근할 권리X
// 404 Not Found        서버는 요청받은 리소스를 찾을 수 없습니다.

@Slf4j
@ControllerAdvice
public class HandlerExample {

    @Autowired
    MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e){
        log.info("========= NullPointerException ==========");
        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(responseEntityDto);
    }

    // @RequestBody 를 쓴 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("========= MethodArgumentNotValidException ==========");
        String errorCode = null;
//        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            log.info("error field = {}", fieldError.getField());
//            log.info("error code = {}", fieldError.getCodes()[0]);
//        });

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            log.info("error field = {}", fieldError.getField());
            log.info("error code = {}", fieldError.getCodes()[0]);
            errorCode = fieldError.getCodes()[0];
        }

        ErrorResponseDto errorResponse = ErrorResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(messageSourceAccessor.getMessage(errorCode))
                        .localDateTime(LocalDateTime.now())
                        .build();

        log.info("{}", errorResponse);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    // 없는 페이지의 경우
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerNoHandlerFoundException(NoHandlerFoundException e){
        log.info("## handlerNoHandlerFoundException");
        return "error/errorMain";
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseBody
    public ResponseEntity<Object> handlerMissingPathVariableException(MissingPathVariableException e){
        log.info("========= MissingPathVariableException ==========");
        /*ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
//                MessageUtils.getMessage(handlerName.methodName.variableName)
                .message("인증코드 확인")
                .localDateTime(LocalDateTime.now())
                .build();*/

        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
//                .message(ErrorCode.BAD_AUTH_REQUEST.getMessage())
                .message(messageSourceAccessor.getMessage("error.badAuth"))
                .localDateTime(LocalDateTime.now())
                .build();


        log.info("variableName = {}", e.getVariableName());
        log.info("methodName = {}", e.getParameter().getMethod().getName().toString());


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseEntityDto);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerIllegalArgumentException(IllegalArgumentException e){
        log.info("========= IllegalArgumentException ==========");
        ResponseEntityDto responseEntityDto = ResponseEntityDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityDto);

    }



    /*@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.info("================= handleIllegalArgumentException ===================");
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Object> handleMissingPathVariableException(MissingPathVariableException e) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }*/
}
