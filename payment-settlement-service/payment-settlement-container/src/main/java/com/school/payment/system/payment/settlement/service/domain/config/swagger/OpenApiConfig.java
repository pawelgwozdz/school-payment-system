package com.school.payment.system.payment.settlement.service.domain.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Swagger info data (config)
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Paweł Gwóźdź",
                        email = "pawel1012001@gmail.com"
                ),
                description = "OpenApi documentation for The Software Partner task",
                title = "School Payment System app, Payment Settlement service",
                version = "1.0"
        )
)
public class OpenApiConfig {
}
