package com.wofang.mybatisplus.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    /**
     * 日志
     */
    private final static Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);

    /**
     * 配置分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor PaginationInterceptor(){
        logger.info("注册分页插件");
        return new PaginationInterceptor();
    }
}
