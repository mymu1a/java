package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeDataConverterMapping;
import artur.maschenko.dateconverter.service.TimeDataConverterMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time-data-converter-mapping")
public class TimeDataConverterMappingController {
    private final TimeDataConverterMappingService timeDataConverterMappingService;

    @Autowired
    public TimeDataConverterMappingController(TimeDataConverterMappingService timeDataConverterMappingService) {
        this.timeDataConverterMappingService = timeDataConverterMappingService;
    }

    // Получить все записи из таблицы TimeDataConverterMapping
    @GetMapping("/")
    public List<TimeDataConverterMapping> getAllTimeDataConverterMappings() {
        return timeDataConverterMappingService.getAllTimeDataConverterMappings();
    }

    // Получить запись из таблицы TimeDataConverterMapping по идентификатору
    @GetMapping("/{id}")
    public Optional<TimeDataConverterMapping> getTimeDataConverterMappingById(@PathVariable Long id) {
        return timeDataConverterMappingService.getTimeDataConverterMappingById(id);
    }

    // Сохранить новую запись в таблице TimeDataConverterMapping
    @PostMapping("/")
    public TimeDataConverterMapping saveTimeDataConverterMapping(@RequestBody TimeDataConverterMapping timeDataConverterMapping) {
        return timeDataConverterMappingService.saveTimeDataConverterMapping(timeDataConverterMapping);
    }

    // Обновить запись в таблице TimeDataConverterMapping по идентификатору
    @PutMapping("/{id}")
    public TimeDataConverterMapping updateTimeDataConverterMapping(@PathVariable Long id, @RequestBody TimeDataConverterMapping timeDataConverterMapping) {
        timeDataConverterMapping.setId(id);
        return timeDataConverterMappingService.saveTimeDataConverterMapping(timeDataConverterMapping);
    }

    // Удалить запись из таблицы TimeDataConverterMapping по идентификатору
    @DeleteMapping("/{id}")
    public void deleteTimeDataConverterMapping(@PathVariable Long id) {
        timeDataConverterMappingService.deleteTimeDataConverterMapping(id);
    }
}
