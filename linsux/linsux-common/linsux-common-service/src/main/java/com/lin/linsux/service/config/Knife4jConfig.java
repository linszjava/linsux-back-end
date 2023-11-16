package com.lin.linsux.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/10/28 14:57
 */
@Configuration
public class Knife4jConfig {

    private static final String ADMIN_GROUP = "admin-api";
    private static final String ADMIN_PATH_MATCH = "/admin/**";
    public static final String ADMIN_TITLE = "LINSUX-API-接口文档";
    public static final String ADMIN_VERSION = "1.2.0";

    @Bean
    public GroupedOpenApi adminOpenApi(){
        return GroupedOpenApi.builder()
                .group(ADMIN_GROUP)
                .pathsToMatch(ADMIN_PATH_MATCH)
                .build();
    }

    @Bean
    public OpenAPI cusOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(ADMIN_TITLE)
                        .version(ADMIN_VERSION)
                        .description(ADMIN_TITLE)
                        .contact(new Contact()
                                .name("LINSZ")
                                .email("linszjava@gmail.com")));
    }
}
