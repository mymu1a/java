package artur.maschenko.dateconverter;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.service.CacheService;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * The type Cache service test.
 */
class CacheServiceTest {

    @Mock
    private TimeDataService timeDataService;

    @InjectMocks
    private CacheService cacheService;

    /**
     * Sets .
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test put.
     */
    @Test
    void testPut() {
        String key = "testKey";
        Object value = "testValue";

        cacheService.put(key, value);

        // Проверяем, что значение успешно добавлено в кеш
        assertTrue(cacheService.containsKey(key));
    }

    /**
     * Test get.
     */
    @Test
    void testGet() {
        String key = "testKey";
        List<TimeData> value = new ArrayList<>(); // Создание списка объектов TimeData

        // Мокируем метод getAllTimeData() сервиса для временных данных
        when(timeDataService.getAllTimeData()).thenReturn(value);

        // Добавляем значение в кеш
        cacheService.put(key, value);

        // Получаем значение из кеша
        Object cachedValue = cacheService.get(key);

        // Проверяем, что полученное значение совпадает с добавленным
        assertEquals(value, cachedValue);
    }

    /**
     * Test remove.
     */
    @Test
    void testRemove() {
        String key = "testKey";
        Object value = "testValue";

        // Добавляем значение в кеш
        cacheService.put(key, value);

        // Удаляем значение из кеша
        cacheService.remove(key);

        // Проверяем, что значение успешно удалено
        assertFalse(cacheService.containsKey(key));
    }
}
