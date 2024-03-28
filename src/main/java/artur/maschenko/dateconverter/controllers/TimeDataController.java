package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time-data")
public class TimeDataController {
    private final TimeDataService timeDataService;

    @Autowired
    public TimeDataController(TimeDataService timeDataService) {
        this.timeDataService = timeDataService;
    }

    // Получить все записи из таблицы TimeData
    @GetMapping("/")
    public List<TimeData> getAllTimeData() {
        return timeDataService.getAllTimeData();
    }

    // Получить запись из таблицы TimeData по идентификатору
    @GetMapping("/{id}")
    public Optional<TimeData> getTimeDataById(@PathVariable Long id) {
        return timeDataService.getTimeDataById(id);
    }

    // Сохранить новую запись в таблице TimeData
    @PostMapping("/")
    public TimeData saveTimeData(@RequestBody TimeData timeData) {
        return timeDataService.saveTimeData(timeData);
    }

    // Обновить запись в таблице TimeData по идентификатору
    @PutMapping("/{id}")
    public TimeData updateTimeData(@PathVariable Long id, @RequestBody TimeData timeData) {
        timeData.setId(id);
        return timeDataService.saveTimeData(timeData);
    }

    // Удалить запись из таблицы TimeData по идентификатору
    @DeleteMapping("/{id}")
    public void deleteTimeData(@PathVariable Long id) {
        timeDataService.deleteTimeData(id);
    }
}
