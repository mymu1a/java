package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.service.TimeDeviceDataService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** The type Time device data controller. */
@RestController
@CrossOrigin
@RequestMapping("/time-devices")
public class TimeDeviceDataController {
  private final TimeDeviceDataService timeDeviceDataService;

  /**
   * Instantiates a new Time device data controller.
   *
   * @param timeDeviceDataService the time device data service
   */
  @Autowired
  public TimeDeviceDataController(TimeDeviceDataService timeDeviceDataService) {
    this.timeDeviceDataService = timeDeviceDataService;
  }

  /**
   * Gets time device data by id.
   *
   * @param id the id
   * @return the time device data by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<TimeDeviceData> getTimeDeviceDataById(@PathVariable Long id) {
    TimeDeviceData timeDeviceData = timeDeviceDataService.getTimeDeviceDataById(id);
    return ResponseEntity.ok().body(timeDeviceData);
  }

  /**
   * Gets all time device data.
   *
   * @return the all time device data
   */
  @GetMapping("/all")
  public ResponseEntity<List<TimeDeviceData>> getAllTimeDeviceData() {
    List<TimeDeviceData> timeDeviceDataList = timeDeviceDataService.getAllTimeDeviceData();
    return ResponseEntity.ok().body(timeDeviceDataList);
  }

  /**
   * Save time device data response entity.
   *
   * @param timeDeviceData the time device data
   * @return the response entity
   */
  @PostMapping("/")
  public ResponseEntity<TimeDeviceData> saveTimeDeviceData(
      @RequestBody TimeDeviceData timeDeviceData) {
    TimeDeviceData savedTimeDeviceData = timeDeviceDataService.saveTimeDeviceData(timeDeviceData);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTimeDeviceData);
  }

  /**
   * Add to time converter response entity.
   *
   * @param timeConverterId the time converter id
   * @param timeDeviceData the time device data
   * @return the response entity
   */
  @PostMapping("/add-to-converter/{timeConverterId}")
  public ResponseEntity<TimeDeviceData> addToTimeConverter(
      @PathVariable Long timeConverterId, @RequestBody TimeDeviceData timeDeviceData) {
    TimeDeviceData savedTimeDeviceData =
        timeDeviceDataService.addToTimeConverter(timeConverterId, timeDeviceData);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedTimeDeviceData);
  }

  /**
   * Update time device data response entity.
   *
   * @param id the id
   * @param newData the new data
   * @return the response entity
   */
  @PutMapping("/{id}")
  public ResponseEntity<TimeDeviceData> updateTimeDeviceData(
      @PathVariable Long id, @RequestBody TimeDeviceData newData) {
    TimeDeviceData updatedTimeDeviceData = timeDeviceDataService.updateTimeDeviceData(id, newData);
    return ResponseEntity.ok().body(updatedTimeDeviceData);
  }

  /**
   * Delete time device data response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTimeDeviceData(@PathVariable Long id) {
    timeDeviceDataService.deleteTimeDeviceData(id);
    return ResponseEntity.noContent().build();
  }
}
