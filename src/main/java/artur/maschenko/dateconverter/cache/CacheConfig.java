package artur.maschenko.dateconverter.cache;

import artur.maschenko.dateconverter.service.CacheService;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The type Cache artur.maschenko.dateconverter.config. */
@Configuration
public class CacheConfig {
  private final TimeDataService timeDataService;

  /**
   * Instantiates a new Cache artur.maschenko.dateconverter.config.
   *
   * @param timeDataService the time data service
   */
  @Autowired
  public CacheConfig(TimeDataService timeDataService) {
    this.timeDataService = timeDataService;
  }

  /**
   * Cache service.
   *
   * @return the cache service
   */
  @Bean
  public CacheService cacheService() {
    return new CacheService(timeDataService);
  }
}
