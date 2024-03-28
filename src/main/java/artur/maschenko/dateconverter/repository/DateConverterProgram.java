package artur.maschenko.dateconverter.repository;

import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Repository
public class DateConverterProgram {

    public Map<String, String> convertTime(long milliseconds) {
        //представление момента времени
        Instant instant = Instant.ofEpochMilli(milliseconds);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));
        //шаблон даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //результаты конвертации времени
        Map<String, String> result = new HashMap<>();

        result.put("local date", localDateTime.format(formatter));
        result.put("gmt date", gmtDateTime.format(formatter));

        return result;
    }
}