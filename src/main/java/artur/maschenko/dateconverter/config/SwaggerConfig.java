package artur.maschenko.dateconverter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * The SwaggerConfig class is responsible for configuring the OpenAPI
 *  documentation for the Web-Text-Translator application.
 */
@OpenAPIDefinition(
        info = @Info(
                title = "date-converter",
                description = "convert-date",
                version = "1.0.0",
                contact = @Contact(
                        name = "Artur Maschenko",
                        email = "vjesrhbvls@gmail.com"
                )
        )
)

public class SwaggerConfig {
}