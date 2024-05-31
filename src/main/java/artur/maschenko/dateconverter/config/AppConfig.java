package artur.maschenko.dateconverter.config;

import artur.maschenko.dateconverter.component.RequestCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RequestCounter requestCounter() {
        return new RequestCounter();
    }
}