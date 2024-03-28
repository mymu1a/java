package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.service.TimeConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time-converter")
public class TimeConverterController {
    @Autowired
    private TimeConverterService timeConverterService;

    // Получить все записи из таблицы TimeConverter
    @GetMapping("/")
    public List<TimeConverter> getAllTimeConverters() {
        return timeConverterService.getAllTimeConverters();
    }

    // Получить запись из таблицы TimeConverter по идентификатору
    @GetMapping("/{id}")
    public Optional<TimeConverter> getTimeConverterById(@PathVariable Long id) {
        return timeConverterService.getTimeConverterById(id);
    }

    // Сохранить новую запись в таблице TimeConverter
    @PostMapping("/")
    public TimeConverter saveTimeConverter(@RequestBody TimeConverter timeConverter) {
        return timeConverterService.saveTimeConverter(timeConverter);
    }

    // Обновить запись в таблице TimeConverter по идентификатору
    @PutMapping("/{id}")
    public TimeConverter updateTimeConverter(@PathVariable Long id, @RequestBody TimeConverter timeConverter) {
        timeConverter.setId(id);
        return timeConverterService.saveTimeConverter(timeConverter);
    }

    // Удалить запись из таблицы TimeConverter по идентификатору
    @DeleteMapping("/{id}")
    public void deleteTimeConverter(@PathVariable Long id) {
        timeConverterService.deleteTimeConverter(id);
    }
}