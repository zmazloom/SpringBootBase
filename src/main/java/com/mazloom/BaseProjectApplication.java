package com.mazloom;

/**
 * The main class of project
 *
 * @author Zahra Mazloom
 * 2024-01-24
 */

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.mazloom.domain.model"})
@EnableJpaRepositories(basePackages = {"com.mazloom"})
public class BaseProjectApplication {

    private static String projectSessionId;

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(DiningApplication.class);
    }

    @Value("${server.servlet.session.cookie.name}")
    public void setProjectSessionId(String projectSessionId) {
        DiningApplication.projectSessionId = projectSessionId;
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
