package artur.maschenko.dateconverter.cache;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class MillisecondsCache {
    private static final int MAX_CACHE_SIZE = 50;
    private Map<Long, Long> cache = new LinkedHashMap<>(MAX_CACHE_SIZE + 1, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, Long> eldest) {
            return super.size() > MAX_CACHE_SIZE;
        }
    };

    public void addMilliseconds(long key, long milliseconds) {
        cache.put(key, milliseconds);
    }

    public Long getMilliseconds(long key) {
        return cache.get(key);
    }

    public void removeMilliseconds(long key) {
        cache.remove(key);
    }

    public boolean containsKey(long key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public Map<Long, Long> getAllMilliseconds() {
        return new LinkedHashMap<>(cache);
    }
}