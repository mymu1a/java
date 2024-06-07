package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.service.TimeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Time data controller.
 */
@RestController
@CrossOrigin
@RequestMapping("/time-data")
public class TimeDataController {
    private final TimeDataService timeDataService;

    /**
     * Instantiates a new Time data controller.
     *
     * @param timeDataService the time data service
     */
    @Autowired
    public TimeDataController(TimeDataService timeDataService) {
        this.timeDataService = timeDataService;
    }

    /**
     * Gets all time data.
     *
     * @return the all time data
     */
    @GetMapping("/")
    public List<TimeData> getAllTimeData() {
        return timeDataService.getAllTimeData();
    }

    /**
     * Gets time data by id.
     *
     * @param id the id
     * @return the time data by id
     */
    @GetMapping("/{id}")
    public Optional<TimeData> getTimeDataById(@PathVariable Long id) {
        return timeDataService.getTimeDataById(id);
    }

    /**
     * Save time data time data.
     *
     * @param timeData the time data
     * @return the time data
     */
    @PostMapping("/")
    public TimeData saveTimeData(@RequestBody TimeData timeData) {
        return timeDataService.saveTimeData(timeData);
    }

    @PostMapping("/bulk/")
    public List<TimeData> saveTimeDataList(@RequestBody List<TimeData> timeDataList) {
        return timeDataList.stream().map(timeDataService::saveTimeData).collect(Collectors.toList());
    }

    /**
     * Update time data time data.
     *
     * @param id       the id
     * @param timeData the time data
     * @return the time data
     */
    @PutMapping("/{id}")
    public TimeData updateTimeData(@PathVariable Long id, @RequestBody TimeData timeData) {
        return timeDataService.updateTimeData(id, timeData);
    }

    /**
     * Delete time data.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteTimeData(@PathVariable Long id) {
        timeDataService.deleteTimeData(id);
    }

    /**
     * Gets max milliseconds.
     *
     * @return the max milliseconds
     */
    @GetMapping("/useful")
    public ResponseEntity<Long> getMaxMilliseconds() {
        Long maxMilliseconds = timeDataService.getMaxMilliseconds();
        return ResponseEntity.ok(maxMilliseconds);
    }

    @GetMapping("/milliseconds/{milliseconds}")
    public List<TimeData> getTimeDataWithMillisecondsGreaterThan(@PathVariable Long milliseconds) {
        return timeDataService.getTimeDataWithMillisecondsGreaterThan(milliseconds);
    }

    @GetMapping("/sumMilliseconds")
    public Long getSumOfMilliseconds() {
        return timeDataService.getSumOfMilliseconds();
    }

    @GetMapping("/time-data/request-count")
    public int getRequestCount() {
        return timeDataService.getRequestCount();
    }
}
