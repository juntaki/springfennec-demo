package com.juntaki.springfennecdemo.application

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringfoxConfig {
    @Bean
    fun DemoApiDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("springfox")
                .select()
                .paths(PathSelectors.ant("/demo/**"))
                .build()
    }

    @Bean
    fun DemoApiDocumentAnotherDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("springfox_suffix_test")
                .select()
                .paths(PathSelectors.ant("/demo/**"))
                .build()
    }
}