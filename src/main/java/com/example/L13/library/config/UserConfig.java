package com.example.L13.library.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "user-info.configuration")
@Slf4j
public class UserConfig implements InitializingBean {

    @Value("${user-info.configuration.book-quota}")
    Integer bookQuota;


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("user info bookQuota {}", bookQuota);
    }
}
