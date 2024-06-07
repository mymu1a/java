package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.service.DateConverterService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** The type Date converter controller. */
@RestController
@CrossOrigin
public class DateConverterController {

  private final DateConverterService dateConverterService;

  /**
   * Instantiates a new Date converter controller.
   *
   * @param dateConverterService the date converter service
   */
  @Autowired
  public DateConverterController(DateConverterService dateConverterService) {
    this.dateConverterService = dateConverterService;
  }

  /**
   * Convert time map.
   *
   * @param milliseconds the milliseconds
   * @return the map
   */
  @GetMapping("/date-convert")
  public Map<String, String> convertTime(@RequestParam long milliseconds) {
    return dateConverterService.convertTime(milliseconds);
  }
}
