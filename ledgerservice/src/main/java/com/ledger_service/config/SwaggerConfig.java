package transactiongateway.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI transactionGatewayAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("SwiftPay Ledger Service API")
                                .description("Ledger Processing APIs")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Shrajan Jain")
                                                .email("your-email@example.com")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("SwiftPay Documentation")
                );
    }
}