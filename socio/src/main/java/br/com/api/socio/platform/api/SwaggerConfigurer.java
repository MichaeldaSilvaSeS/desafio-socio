package br.com.api.socio.platform.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.api.socio.Application;
import br.com.api.socio.controller.SocioController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = Application.class)
public class SwaggerConfigurer {
    
    private static final String PACKAGE_BASE = SocioController.class.getPackage().getName();
    
    @Bean
    public Docket swaggerInit() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(PACKAGE_BASE))
                .paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
    }
}