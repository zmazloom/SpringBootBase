package com.mazloom.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ProjectConfiguration {

    @Value("${project.url}")
    private String projectUrlConfig;
    @Value("${server.version.code}")
    private String serverVersion;

}
