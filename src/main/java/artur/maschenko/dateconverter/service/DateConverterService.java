package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.models.TimeDataConverterMapping;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import artur.maschenko.dateconverter.repository.TimeDataConverterMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class DateConverterService {

    private final TimeDataRepository timeDataRepository;
    private final TimeConverterRepository timeConverterRepository;
    private final TimeDeviceDataRepository timeDeviceDataRepository;
    private final TimeDataConverterMappingRepository timeDataConverterMappingRepository; // Добавлено

    @Autowired
    public DateConverterService(TimeDataRepository timeDataRepository, TimeConverterRepository timeConverterRepository, TimeDeviceDataRepository timeDeviceDataRepository, TimeDataConverterMappingRepository timeDataConverterMappingRepository) {
        this.timeDataRepository = timeDataRepository;
        this.timeConverterRepository = timeConverterRepository;
        this.timeDeviceDataRepository = timeDeviceDataRepository;
        this.timeDataConverterMappingRepository = timeDataConverterMappingRepository;
    }

    public Map<String, String> convertTime(long milliseconds) {
        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Сохранение данных в базу данных
        TimeData timeData = new TimeData();
        timeData.setMilliseconds(milliseconds);
        timeDataRepository.save(timeData);

        TimeConverter timeConverter = new TimeConverter();
        timeConverter.setLocalTime(localDateTime);
        timeConverter.setGmtTime(gmtDateTime);
        timeConverterRepository.save(timeConverter);

        // Создание и сохранение связи TimeDataConverterMapping
        TimeDataConverterMapping timeDataConverterMapping = new TimeDataConverterMapping();
        timeDataConverterMapping.setTimeData(timeData);
        timeDataConverterMapping.setTimeConverter(timeConverter);
        timeDataConverterMappingRepository.save(timeDataConverterMapping);

        TimeDeviceData timeDeviceData = new TimeDeviceData();
        timeDeviceData.setDeviceTime(LocalDateTime.now()); // Текущее время устройства
        timeDeviceData.setTimeData(timeData); // Связываем сущность TimeDeviceData с TimeData
        timeDeviceDataRepository.save(timeDeviceData);

        Map<String, String> result = new HashMap<>();
        result.put("local date", localDateTime.format(formatter));
        result.put("gmt date", gmtDateTime.format(formatter));
        return result;
    }
}