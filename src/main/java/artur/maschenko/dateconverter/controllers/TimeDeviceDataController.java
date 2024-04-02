package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.service.TimeDeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/time-devices")
public class TimeDeviceDataController {
    private final TimeDeviceDataService timeDeviceDataService;

    @Autowired
    public TimeDeviceDataController(TimeDeviceDataService timeDeviceDataService) {
        this.timeDeviceDataService = timeDeviceDataService;
    }

    @GetMapping("/useful")
    public LocalDateTime getLastDeviceTime() {
        return timeDeviceDataService.getLastDeviceTime();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeDeviceData> getTimeDeviceDataById(@PathVariable Long id) {
        TimeDeviceData timeDeviceData = timeDeviceDataService.getTimeDeviceDataById(id);
        return ResponseEntity.ok().body(timeDeviceData);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimeDeviceData>> getAllTimeDeviceData() {
        List<TimeDeviceData> timeDeviceDataList = timeDeviceDataService.getAllTimeDeviceData();
        return ResponseEntity.ok().body(timeDeviceDataList);
    }

    @PostMapping("/")
    public ResponseEntity<TimeDeviceData> saveTimeDeviceData(@RequestBody TimeDeviceData timeDeviceData) {
        TimeDeviceData savedTimeDeviceData = timeDeviceDataService.saveTimeDeviceData(timeDeviceData);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTimeDeviceData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeDeviceData> updateTimeDeviceData(@PathVariable Long id, @RequestBody TimeDeviceData newData) {
        TimeDeviceData updatedTimeDeviceData = timeDeviceDataService.updateTimeDeviceData(id, newData);
        return ResponseEntity.ok().body(updatedTimeDeviceData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeDeviceData(@PathVariable Long id) {
        timeDeviceDataService.deleteTimeDeviceData(id);
        return ResponseEntity.noContent().build();
    }
}