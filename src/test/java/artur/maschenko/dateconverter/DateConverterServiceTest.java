package artur.maschenko.dateconverter;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import artur.maschenko.dateconverter.service.DateConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * The type Date converter service test.
 */
class DateConverterServiceTest {

  @Mock private TimeDataRepository timeDataRepository;

  @Mock private TimeConverterRepository timeConverterRepository;

  @Mock private TimeDeviceDataRepository timeDeviceDataRepository;

  @InjectMocks private DateConverterService dateConverterService;

  /**
   * Sets up.
   */
@BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test convert time.
   */
  @Test
  void testConvertTime() {
    long milliseconds = 1620237600000L; // Example milliseconds value
    LocalDateTime localDateTime =
            LocalDateTime.ofInstant(
                    java.time.Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    LocalDateTime gmtDateTime =
            LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(milliseconds), ZoneId.of("GMT"));

    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setLocalTime(localDateTime);
    timeConverter.setGmtTime(gmtDateTime);

    when(timeConverterRepository.save(any(TimeConverter.class))).thenReturn(timeConverter);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Map<String, String> expectedResult = new HashMap<>();
    expectedResult.put("local date", localDateTime.format(formatter));
    expectedResult.put("gmt date", gmtDateTime.format(formatter));

    Map<String, String> result = dateConverterService.convertTime(milliseconds);

    assertEquals(expectedResult, result);
    verify(timeConverterRepository, times(1)).save(any(TimeConverter.class));
  }

  /** Test add device time to converter. */
  @Test
  void testAddDeviceTimeToConverter() {
    long converterId = 1L; // Example converter ID
    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setId(converterId);

    when(timeConverterRepository.findById(converterId)).thenReturn(Optional.of(timeConverter));

    dateConverterService.addDeviceTimeToConverter(converterId);

    verify(timeConverterRepository, times(1)).findById(converterId);
    verify(timeDeviceDataRepository, times(1)).save(any(TimeDeviceData.class));
  }
}
