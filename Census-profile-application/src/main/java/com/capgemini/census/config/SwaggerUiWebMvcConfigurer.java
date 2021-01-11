package com.capgemini.census.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://springfox.github.io/springfox/docs/snapshot/


@Component
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {
  private final String baseUrl;

  public SwaggerUiWebMvcConfigurer(
      @Value("${springfox.documentation.swagger-ui.base-url:}") String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /**
   * This method enables Swagger-UI 
   * part for visual documentation.
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
    registry.
        addResourceHandler(baseUrl + "/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
  }

  /**
   * This methods add the view to the controller
   */
  //http://localhost:8081/springfox/swagger-ui/index.html
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController(baseUrl + "/swagger-ui/")
        .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
  }
  
/**
 * Cross-Origin Resource Sharing (CORS) is a security 
 * concept that allows restricting the resources implemented
 *  in web browsers
 */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/api/member")
        .allowedOrigins("http://editor.swagger.io");
    registry
        .addMapping("/v2/api-docs.*")
        .allowedOrigins("http://editor.swagger.io");
    registry
    .addMapping("/api/member")
    .allowedOrigins("*");
   
  }
}