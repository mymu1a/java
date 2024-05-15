package artur.maschenko.dateconverter;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import artur.maschenko.dateconverter.service.TimeDataService;
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

/** The type Time data service test. */
public class TimeDataServiceTest {

  @Mock private TimeDataRepository timeDataRepository;

  @InjectMocks private TimeDataService timeDataService;

  /** Sets up. */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /** Test get all time data. */
  @Test
  void testGetAllTimeData() {
    List<TimeData> expectedList = Collections.singletonList(new TimeData());
    when(timeDataRepository.findAll()).thenReturn(expectedList);

    List<TimeData> result = timeDataService.getAllTimeData();

    assertEquals(expectedList, result);
    verify(timeDataRepository, times(1)).findAll();
  }

  /** Test get time data by id. */
  @Test
  void testGetTimeDataById() {
    long id = 1L;
    TimeData expected = new TimeData();
    expected.setId(id);

    when(timeDataRepository.findById(id)).thenReturn(Optional.of(expected));

    Optional<TimeData> result = timeDataService.getTimeDataById(id);

    assertEquals(Optional.of(expected), result);
    verify(timeDataRepository, times(1)).findById(id);
  }

  /** Test save time data. */
  @Test
  void testSaveTimeData() {
    TimeData timeData = new TimeData();
    when(timeDataRepository.save(any(TimeData.class))).thenReturn(timeData);

    TimeData result = timeDataService.saveTimeData(timeData);

    assertEquals(timeData, result);
    verify(timeDataRepository, times(1)).save(any(TimeData.class));
  }

  /** Test update time data. */
  @Test
  void testUpdateTimeData() {
    long id = 1L;
    TimeData timeData = new TimeData();
    timeData.setId(id);

    when(timeDataRepository.save(any(TimeData.class))).thenReturn(timeData);

    TimeData result = timeDataService.updateTimeData(id, timeData);

    assertEquals(timeData, result);
    verify(timeDataRepository, times(1)).save(timeData);
  }

  /** Test delete time data. */
  @Test
  void testDeleteTimeData() {
    long id = 1L;

    timeDataService.deleteTimeData(id);

    verify(timeDataRepository, times(1)).deleteById(id);
  }

  /** Test get max milliseconds. */
  @Test
  void testGetMaxMilliseconds() {
    long expectedMaxMilliseconds = 1000L;
    when(timeDataRepository.findMaxMilliseconds()).thenReturn(expectedMaxMilliseconds);

    Long result = timeDataService.getMaxMilliseconds();

    assertEquals(expectedMaxMilliseconds, result);
    verify(timeDataRepository, times(1)).findMaxMilliseconds();
  }
}
