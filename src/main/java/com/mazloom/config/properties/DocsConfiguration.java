package com.mazloom.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class DocsConfiguration {

    @Value("${swagger.title}")
    private String swaggerTitle;
    @Value("${swagger.description}")
    private String swaggerDescription;
    @Value("${swagger.terms-of-service}")
    private String swaggerTermsOfService;
    @Value("${docs.private.username}")
    private String docsPrivateUsername;
    @Value("${docs.private.password}")
    private String docsPrivatePassword;
    @Value("${project.public-paths}")
    private String swaggerPublicPaths;
    @Value("${project.private-paths}")
    private String swaggerPrivatePaths;

}
