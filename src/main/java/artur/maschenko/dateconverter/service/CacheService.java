package artur.maschenko.dateconverter.service;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheService {
    private final TimeDataService timeDataService;
    private final Map<String, Object> cache = new LinkedHashMap<>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > 10;
        }
    };

    public CacheService(TimeDataService timeDataService) {
        this.timeDataService = timeDataService;
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        Object value = cache.get(key);
        if (value == null) {
            if ("timeData".equals(key)) {
                value = timeDataService.getAllTimeData();
            } else if ("maxMilliseconds".equals(key)) {
                value = timeDataService.getMaxMilliseconds();
            }
            cache.put(key, value);
        }
        return value;
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}