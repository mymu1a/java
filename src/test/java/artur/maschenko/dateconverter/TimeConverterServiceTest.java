package artur.maschenko.dateconverter;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import artur.maschenko.dateconverter.service.TimeConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/** The type Time converter service test. */
class TimeConverterServiceTest {

  @Mock private TimeConverterRepository timeConverterRepository;

  @InjectMocks private TimeConverterService timeConverterService;

  /** Sets up. */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /** Test get all time converters. */
  @Test
  void testGetAllTimeConverters() {
    List<TimeConverter> expectedList = Collections.singletonList(new TimeConverter());
    when(timeConverterRepository.findAll()).thenReturn(expectedList);

    List<TimeConverter> result = timeConverterService.getAllTimeConverters();

    assertEquals(expectedList, result);
    verify(timeConverterRepository, times(1)).findAll();
  }

  /** Test get time converter by id. */
  @Test
  void testGetTimeConverterById() {
    long id = 1L;
    TimeConverter expected = new TimeConverter();
    expected.setId(id);

    when(timeConverterRepository.findById(id)).thenReturn(Optional.of(expected));

    Optional<TimeConverter> result = timeConverterService.getTimeConverterById(id);

    assertEquals(Optional.of(expected), result);
    verify(timeConverterRepository, times(1)).findById(id);
  }

  /** Test save time converter. */
  @Test
  void testSaveTimeConverter() {
    TimeConverter timeConverter = new TimeConverter();
    when(timeConverterRepository.save(any(TimeConverter.class))).thenReturn(timeConverter);

    TimeConverter result = timeConverterService.saveTimeConverter(timeConverter);

    assertEquals(timeConverter, result);
    verify(timeConverterRepository, times(1)).save(timeConverter);
  }

  /** Test update time converter. */
  @Test
  void testUpdateTimeConverter() {
    long id = 1L;
    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setId(id);

    when(timeConverterRepository.save(any(TimeConverter.class))).thenReturn(timeConverter);

    TimeConverter result = timeConverterService.updateTimeConverter(id, timeConverter);

    assertEquals(timeConverter, result);
    verify(timeConverterRepository, times(1)).save(timeConverter);
  }

  /** Test delete time converter. */
  @Test
  void testDeleteTimeConverter() {
    long id = 1L;

    timeConverterService.deleteTimeConverter(id);

    verify(timeConverterRepository, times(1)).deleteById(id);
  }
}
