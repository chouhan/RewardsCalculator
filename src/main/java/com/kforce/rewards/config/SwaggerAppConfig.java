package com.kforce.rewards.config;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.time.LocalDate;

/**
 * private ApiKey apiKey() { return new ApiKey("mykey", "api_key",* "header")}
 * private SecurityContext securityContext() { return
 * SecurityContext.builder().securityReferences(defaultAuth())
 * .forPaths(PathSelectors.regex("/anyPath.*")).build(); }
 * <p>
 * List<SecurityReference> defaultAuth() { AuthorizationScope
 * authorizationScope = new AuthorizationScope( "global",
 * "accessEverything"); AuthorizationScope[] authorizationScopes = new
 * AuthorizationScope[1]; authorizationScopes[0] = authorizationScope;
 * return newArrayList(new SecurityReference("mykey", authorizationScopes));
 * }
 *
 * @author Rakesh Chouhan
 */

@Configuration
@EnableSwagger2WebMvc
@ComponentScan(basePackages = "com.kforce.rewards.controller")
public class SwaggerAppConfig {

    @Value("${swagger.submit.methods}")
    private String[] submitMethods;

    /**
     * .globalResponseMessage( RequestMethod.GET, newArrayList(new
     * ResponseMessageBuilder().code(500) .message("500 message")
     * .responseModel(new ModelRef("Error")).build()))
     * .securitySchemes(newArrayList(apiKey()))
     * .securityContexts(newArrayList(securityContext()))
     * .enableUrlTemplating(true);
     *
     * @return
     */
    @Bean
    public Docket rewardsApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build()
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Rewards API Documentation")
                .description("Rewards Calculator REST API's")
                .version("1.0")
                .contact(new Contact("me@rakeshchouhan.com", null, null))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
