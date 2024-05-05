package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** The type Date converter service. */
@Service
public class DateConverterService {
  private static final Logger logger = LoggerFactory.getLogger(DateConverterService.class);
  private final TimeDataRepository timeDataRepository;
  private final TimeConverterRepository timeConverterRepository;
  private final TimeDeviceDataRepository timeDeviceDataRepository;

  /**
   * Instantiates a new Date converter service.
   *
   * @param timeDataRepository the time data repository
   * @param timeConverterRepository the time converter repository
   * @param timeDeviceDataRepository the time device data repository
   */
  @Autowired
  public DateConverterService(
      TimeDataRepository timeDataRepository,
      TimeConverterRepository timeConverterRepository,
      TimeDeviceDataRepository timeDeviceDataRepository) {
    this.timeDataRepository = timeDataRepository;
    this.timeConverterRepository = timeConverterRepository;
    this.timeDeviceDataRepository = timeDeviceDataRepository;
  }

  /**
   * Convert time map.
   *
   * @param milliseconds the milliseconds
   * @return the map
   */
  public Map<String, String> convertTime(long milliseconds) {
    logger.info("Converting time for milliseconds: {}", milliseconds);
    Instant instant = Instant.ofEpochMilli(milliseconds);
    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

    TimeData timeData = new TimeData();
    timeData.setMilliseconds(milliseconds);
    timeDataRepository.save(timeData);

    LocalDateTime gmtDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("GMT"));

    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setLocalTime(localDateTime);
    timeConverter.setGmtTime(gmtDateTime);
    timeConverterRepository.save(timeConverter);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Map<String, String> result = new HashMap<>();
    result.put("local date", localDateTime.format(formatter));
    result.put("gmt date", gmtDateTime.format(formatter));
    return result;
  }

  /**
   * Add device time to converter.
   *
   * @param converterId the converter id
   */
  public void addDeviceTimeToConverter(Long converterId) {
    logger.info("Adding device time to converter with ID: {}", converterId);
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

  /**
   * Gets max milliseconds.
   *
   * @return the max milliseconds
   */
  public Long getMaxMilliseconds() {
    logger.info("Getting max milliseconds");
    return timeDataRepository.findMaxMilliseconds();
  }
}
