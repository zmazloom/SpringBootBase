package com.mazloom.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ProjectConfiguration {

    @Value("${notise.url}")
    private String projectUrlConfig;
    @Value("${server.version.name}")
    private String serverName;
    @Value("${server.version.code}")
    private String serverVersion;
    @Value("${server.ip}")
    private String serverIp;
    @Value("${notise.method.limit.execution-time}")
    private Long methodLimitExecutionTime;
    @Value("${environment}")
    private String environment;

}
