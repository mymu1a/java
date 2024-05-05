package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.service.TimeConverterService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** The type Time converter controller. */
@RestController
@RequestMapping("/time-converter")
public class TimeConverterController {
  private final TimeConverterService timeConverterService;

  /**
   * Instantiates a new Time converter controller.
   *
   * @param timeConverterService the time converter service
   */
  @Autowired
  public TimeConverterController(TimeConverterService timeConverterService) {
    this.timeConverterService = timeConverterService;
  }

  /**
   * Gets all time converters.
   *
   * @return the all time converters
   */
  @GetMapping("/")
  public List<TimeConverter> getAllTimeConverters() {
    return timeConverterService.getAllTimeConverters();
  }

  /**
   * Gets time converter by id.
   *
   * @param id the id
   * @return the time converter by id
   */
  @GetMapping("/{id}")
  public Optional<TimeConverter> getTimeConverterById(@PathVariable Long id) {
    return timeConverterService.getTimeConverterById(id);
  }

  /**
   * Save time converter time converter.
   *
   * @param timeConverter the time converter
   * @return the time converter
   */
  @PostMapping("/")
  public TimeConverter saveTimeConverter(@RequestBody TimeConverter timeConverter) {
    return timeConverterService.saveTimeConverter(timeConverter);
  }

  /**
   * Update time converter time converter.
   *
   * @param id the id
   * @param timeConverter the time converter
   * @return the time converter
   */
  @PutMapping("/{id}")
  public TimeConverter updateTimeConverter(
      @PathVariable Long id, @RequestBody TimeConverter timeConverter) {
    timeConverter.setId(id);
    return timeConverterService.saveTimeConverter(timeConverter);
  }

  /**
   * Delete time converter.
   *
   * @param id the id
   */
  @DeleteMapping("/{id}")
  public void deleteTimeConverter(@PathVariable Long id) {
    timeConverterService.deleteTimeConverter(id);
  }
}
