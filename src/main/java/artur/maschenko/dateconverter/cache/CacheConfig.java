package artur.maschenko.dateconverter.cache;

import artur.maschenko.dateconverter.service.CacheService;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    private final TimeDataService timeDataService;

    @Autowired
    public CacheConfig(TimeDataService timeDataService) {
        this.timeDataService = timeDataService;
    }

    @Bean
    public CacheService cacheService() {
        return new CacheService(timeDataService);
    }
}