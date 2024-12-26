package com.demo.security01.config;

import com.demo.security01.config.resolver.CommentArgumentResolver;
import com.demo.security01.config.resolver.LoungeRequestArgumentResolver;
import com.demo.security01.model.dto.community.LoungeWriteRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("==== resourcePath called ===== ");
        //    @Value("${file.dir}")
        String resourcePath = "file:///C:/test/springboot/upload/";
        registry
                .addResourceHandler("/api/profileImages/**")
                .addResourceLocations(resourcePath);
    }*/

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoungeRequestArgumentResolver());
        resolvers.add(new CommentArgumentResolver());
//        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("==== resourcePath called ===== ");
        //    @Value("${file.dir}")
        String resourcePath = "file:///C:/test/springboot/upload/";
        registry
                .addResourceHandler("/api/profileImages/**")
                .addResourceLocations(resourcePath);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.stream().forEach(converter -> {
//            if (converter instanceof MappingJackson2HttpMessageConverter){
//                ((MappingJackson2HttpMessageConverter) converter).setObjectMapper(jsonDateTimeFormat());
//            }else if (converter instanceof StringHttpMessageConverter){
//                ((StringHttpMessageConverter) converter).setDefaultCharset(StandardCharsets.UTF_8);
//            }
//        });


        WebMvcConfigurer.super.extendMessageConverters(converters);
    }

    @Bean
    public ObjectMapper jsonDateTimeFormat(){
        log.info("=== JsonDateTime Format called.... ====");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
                .json()
                .timeZone(TimeZone.getTimeZone("Asia/Seoul"))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                .build();
//        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

 // Cors 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:8060")
			.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
	
	}

    
    
    
    //    ${root}api/profileImages/

    /*@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PagingConverter2());
    }*/

    // mustache 연결 설정
/*@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");

        registry.viewResolver(resolver);


    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        log.info("## configurePathMatch");
        WebMvcConfigurer.super.configurePathMatch(configurer);
    }
    */


//    @Bean
//    public FilterRegistrationBean filterBean() {
//        log.info("## FilterRegistrationBean");
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LogFilter());
//        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        registrationBean.setUrlPatterns(Arrays.asList("/*"));
//        registrationBean.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
//
//        return registrationBean;
//    }

    /*@Bean
    ConversionService myConversionService() {
        DefaultConversionService myConversionService = new DefaultConversionService();
        myConversionService.canConvert()
        myConversionService.addConverter(new PagingConverter2());
        return
    }*/
}
