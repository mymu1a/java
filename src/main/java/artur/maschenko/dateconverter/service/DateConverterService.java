package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DateConverterService {

    private final TimeDataRepository timeDataRepository;
    private final TimeConverterRepository timeConverterRepository;
    private final TimeDeviceDataRepository timeDeviceDataRepository;

    @Autowired
    public DateConverterService(TimeDataRepository timeDataRepository, TimeConverterRepository timeConverterRepository, TimeDeviceDataRepository timeDeviceDataRepository) {
        this.timeDataRepository = timeDataRepository;
        this.timeConverterRepository = timeConverterRepository;
        this.timeDeviceDataRepository = timeDeviceDataRepository;
    }

    public Map<String, String> convertTime(long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        TimeData timeData = new TimeData();
        timeData.setMilliseconds(milliseconds);
        timeDataRepository.save(timeData);

        TimeConverter timeConverter = new TimeConverter();
        timeConverter.setLocalTime(localDateTime);
        timeConverter.setGmtTime(gmtDateTime);
        timeConverterRepository.save(timeConverter);

        Map<String, String> result = new HashMap<>();
        result.put("local date", localDateTime.format(formatter));
        result.put("gmt date", gmtDateTime.format(formatter));
        return result;
    }

    public void addDeviceTimeToConverter(Long converterId) {
        Optional<TimeConverter> optionalTimeConverter = timeConverterRepository.findById(converterId);
        if (optionalTimeConverter.isPresent()) {
            TimeConverter timeConverter = optionalTimeConverter.get();
            LocalDateTime deviceTime = LocalDateTime.now();

            TimeDeviceData timeDeviceData = new TimeDeviceData();
            timeDeviceData.setDeviceTime(deviceTime);
            timeDeviceData.setTimeConverter(timeConverter);
            timeDeviceDataRepository.save(timeDeviceData);
        } else {
            throw new IllegalArgumentException("TimeConverter not found with id: " + converterId);
        }
    }
}
