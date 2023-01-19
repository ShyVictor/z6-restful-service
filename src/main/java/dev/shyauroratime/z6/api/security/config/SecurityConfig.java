package dev.shyauroratime.z6.api.security.config;

import dev.shyauroratime.z6.api.security.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Value("${app.token}")
    private String token;

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter(token);
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> filterRegistrationBean() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(tokenFilter());
        registrationBean.addUrlPatterns("/z6/*");
        return registrationBean;
    }
}