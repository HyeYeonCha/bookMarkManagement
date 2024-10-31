package com.management.bookmarkmanagement.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class WebConfiguration: WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(BearerTokenInterceptor())
            .excludePathPatterns(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-resources/**",
                "/user/auth/v1/**",
            )
    }

}