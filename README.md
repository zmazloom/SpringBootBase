# SpringBootBase

Basic code for Spring boot project

## Packages

- config: General settings of different sections.
- data: Entity classes.
- domain:
    - Request : API inputs that is taken from the user.
    - Srv : API responses
    - VO : It is used in the internal classes of the project to transfer information.
- exception: Exception classes to handle different types of errors. ExceptionHandler of spring boot is located in this
  package.
- food: For each Object of project that has some related APIs, you must create a package with its name and put
  controller, service and repository classes in it.
- message: Classes of code messages in appropriate categories.
- security: classes related to project security. You can customize Authentication/Authorization in AuthorizationFilter
  class.
- utils: General classes with static methods that used in all classes.

## Swagger

Swagger link: http://localhost:8017/api/v1/docs

## Run project

- Install JDK on your system and route it as its JAVA_HOME variable in environment variables.
- Install Maven.
- Go to the project path via cmd and execute the following command to execute the project:
  mvn spring-boot: run
- Sample CURL: curl --location 'localhost:8017/api/v1/food' --header 'Authorization: token'
