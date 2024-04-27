package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/")
    public List<TimeData> getAllTimeData() {
        return timeDataService.getAllTimeData();
    }

    @GetMapping("/{id}")
    public Optional<TimeData> getTimeDataById(@PathVariable Long id) {
        return timeDataService.getTimeDataById(id);
    }

    @PostMapping("/")
    public TimeData saveTimeData(@RequestBody TimeData timeData) {
        return timeDataService.saveTimeData(timeData);
    }

    @PutMapping("/{id}")
    public TimeData updateTimeData(@PathVariable Long id, @RequestBody TimeData timeData) {
        return timeDataService.updateTimeData(id, timeData);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeData(@PathVariable Long id) {
        timeDataService.deleteTimeData(id);
    }

    @GetMapping("/useful")
    public ResponseEntity<Long> getMaxMilliseconds() {
        Long maxMilliseconds = timeDataService.getMaxMilliseconds();
        return ResponseEntity.ok(maxMilliseconds);
    }
}
