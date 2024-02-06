package com.demo.security01.config.handler;

import lombok.Getter;

@Getter
public abstract class GlobalException extends RuntimeException {

    public GlobalException(String msg){
        super(msg);
    }


}
