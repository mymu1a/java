package artur.maschenko.dateconverter.service;

import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/** The type Cache service. */
@Component
public class CacheService {
  private static final Logger logger = LoggerFactory.getLogger(CacheService.class);
  private final TimeDataService timeDataService;
  private final Map<String, Object> cache =
      new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
          return size() > 10;
        }
      };

  /**
   * Instantiates a new Cache service.
   *
   * @param timeDataService the time data service
   */
  public CacheService(TimeDataService timeDataService) {
    this.timeDataService = timeDataService;
  }

  /**
   * Put.
   *
   * @param key the key
   * @param value the value
   */
  public void put(String key, Object value) {
    cache.put(key, value);
    logger.info("Put key '{}' into cache", key);
  }

  /**
   * Get object.
   *
   * @param key the key
   * @return the object
   */
  public Object get(String key) {
    logger.info("Getting value for key '{}' from cache", key);
    return cache.computeIfAbsent(
        key,
        k -> {
          if ("timeData".equals(k)) {
            return timeDataService.getAllTimeData();
          } else if ("maxMilliseconds".equals(k)) {
            return timeDataService.getMaxMilliseconds();
          }
          return null;
        });
  }

  /**
   * Remove.
   *
   * @param key the key
   */
  public void remove(String key) {
    logger.info("Removing key '{}' from cache", key);
    cache.remove(key);
  }

  /**
   * Contains key boolean.
   *
   * @param key the key
   * @return the boolean
   */
  public boolean containsKey(String key) {
    logger.info("Checking if cache contains key '{}'", key);
    return cache.containsKey(key);
  }
}
