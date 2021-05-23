package com.felype.creditcard.config;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Locale;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@ComponentScan("com.felype.creditcard")
@RequiredArgsConstructor
public class DocumentationConfiguration {

    private final TypeResolver resolver;

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder()
                        .title(applicationName)
                        .version(buildVersion)
                        .build())
                .genericModelSubstitutes(
                        Mono.class, Flux.class,
                        java.util.Optional.class)
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(WebRequest.class, Locale.class, BindingResult.class, ResponseStatusException.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        resolver.resolve(Mono.class, WildcardType.class),
                        resolver.resolve(WildcardType.class), HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

}
