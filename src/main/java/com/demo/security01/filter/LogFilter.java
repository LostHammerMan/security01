package com.demo.security01.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("## {}", this.getClass().getSimpleName());

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        String dispatcherType = httpServletRequest.getDispatcherType().toString();
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        log.info("Request: {}, {}", requestURI, dispatcherType);
        log.info("\t contentTye : {}", httpServletRequest.getContentType());
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = httpServletRequest.getHeader(headerName);
            log.info("\t {} : {}", headerName, value);
        }

        chain.doFilter(request, response);
        log.info("Response: {}, {}", requestURI, dispatcherType);
    }
}
