package com.mazloom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class of project
 *
 * @author Zahra Mazloom
 * 2024-01-24
 */

@EnableConfigurationProperties
@EntityScan(basePackages = {"com.mazloom.domain.model"})
@EnableJpaRepositories(basePackages = {"com.mazloom"})
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class BaseProjectApplication {

    private static String projectSessionId;

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(BaseProjectApplication.class);
    }

    @Value("${server.servlet.session.cookie.name}")
    public void setProjectSessionId(String projectSessionId) {
        BaseProjectApplication.projectSessionId = projectSessionId;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.getSessionCookieConfig().setName(projectSessionId);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
