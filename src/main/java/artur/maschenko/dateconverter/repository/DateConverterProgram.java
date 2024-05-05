package artur.maschenko.dateconverter.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/** The type Date converter program. */
@Component
public class DateConverterProgram {

  /**
   * Convert time map.
   *
   * @param milliseconds the milliseconds
   * @return the map
   */
  public Map<String, String> convertTime(long milliseconds) {
    Instant instant = Instant.ofEpochMilli(milliseconds);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Map<String, String> result = new HashMap<>();
    result.put("localDateTime", localDateTime.format(formatter));
    result.put("gmtDateTime", gmtDateTime.format(formatter));

    return result;
  }
}
