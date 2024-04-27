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
        return cache.computeIfAbsent(key, k -> {
            if ("timeData".equals(k)) {
                return timeDataService.getAllTimeData();
            } else if ("maxMilliseconds".equals(k)) {
                return timeDataService.getMaxMilliseconds();
            }
            return null;
        });
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public boolean containsKey(String key) {
        return cache.containsKey(key);
    }
}