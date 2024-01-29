package com.mazloom.config;

import com.mazloom.config.properties.DocsConfiguration;
import com.mazloom.config.properties.ProjectConfiguration;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Configuration class for swagger
 */

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfiguration {

    private final DocsConfiguration docsConfiguration;
    private final ProjectConfiguration projectConfiguration;

    @Bean
    public Docket publicApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("public")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build()
                .host(projectConfiguration.getProjectUrlConfig())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(securityContexts())
                .apiInfo(apiEndPointsInfo());

    }

    @Bean
    public Docket privateApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("private")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(getPrivatePaths())
                .build()
                .host(projectConfiguration.getProjectUrlConfig())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(securityContexts())
                .apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder()
                .title(docsConfiguration.getSwaggerTitle())
                .description(docsConfiguration.getSwaggerDescription())
                .termsOfServiceUrl(docsConfiguration.getSwaggerTermsOfService())
                .version(projectConfiguration.getServerVersion())
                .build();

    }

    private List<SecurityContext> securityContexts() {

        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .build()
        );

    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];

        authorizationScopes[0] = authorizationScope;

        return Collections.singletonList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));

    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header");
    }

    @Bean
    public UiConfiguration uiConfig() {

        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .docExpansion(DocExpansion.LIST)
                .filter(true)
                .build();

    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .enableCsrfSupport(false)
                .build();
    }

    @ApiIgnore
    @RestController
    public static class Home {

        @Value("${swagger.redirect-url}")
        private String swaggerRedirectUrl;

        @GetMapping("/docs")
        public ModelAndView help(ModelMap model, HttpServletRequest request) {

            String sharp = request.getServletPath().substring(5);
            String redirect;

            if (StringUtils.isNotEmpty(sharp) && !sharp.equals("/")) {
                redirect = "redirect:" + swaggerRedirectUrl + "#" + sharp;
            } else {
                redirect = "redirect:" + swaggerRedirectUrl;
            }

            return new ModelAndView(redirect, model);

        }

    }

    private @NotNull Predicate<String> getPrivatePaths() {
        return PathSelectors.ant(docsConfiguration.getSwaggerPrivatePaths());
    }

}
