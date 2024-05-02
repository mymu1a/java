package artur.maschenko.dateconverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import artur.maschenko.dateconverter.service.TimeDeviceDataService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * The type Time device data service test.
 */
public class TimeDeviceDataServiceTest {

  @Mock private TimeDeviceDataRepository timeDeviceDataRepository;

  @InjectMocks private TimeDeviceDataService timeDeviceDataService;

  /**
   * Sets up.
   */
@BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  /**
   * Test get all time device data.
   */
@Test
  public void testGetAllTimeDeviceData() {
    List<TimeDeviceData> expectedList = Collections.singletonList(new TimeDeviceData());
    when(timeDeviceDataRepository.findAll()).thenReturn(expectedList);

    List<TimeDeviceData> result = timeDeviceDataService.getAllTimeDeviceData();

    assertEquals(expectedList, result);
    verify(timeDeviceDataRepository, times(1)).findAll();
  }

  /**
   * Test get time device data by id.
   */
@Test
  public void testGetTimeDeviceDataById() {
    long id = 1L;
    TimeDeviceData expected = new TimeDeviceData();
    expected.setId(id);

    when(timeDeviceDataRepository.findById(id)).thenReturn(Optional.of(expected));

    TimeDeviceData result = timeDeviceDataService.getTimeDeviceDataById(id);

    assertEquals(expected, result);
    verify(timeDeviceDataRepository, times(1)).findById(id);
  }

  /**
   * Test save time device data.
   */
@Test
  public void testSaveTimeDeviceData() {
    TimeDeviceData timeDeviceData = new TimeDeviceData();
    when(timeDeviceDataRepository.save(any(TimeDeviceData.class))).thenReturn(timeDeviceData);

    TimeDeviceData result = timeDeviceDataService.saveTimeDeviceData(timeDeviceData);

    assertEquals(timeDeviceData, result);
    verify(timeDeviceDataRepository, times(1)).save(timeDeviceData);
  }

  /**
   * Test add to time converter.
   */
@Test
  public void testAddToTimeConverter() {
    long converterId = 1L;
    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setId(converterId);

    TimeDeviceData timeDeviceData = new TimeDeviceData();
    timeDeviceData.setTimeConverter(timeConverter);

    when(timeDeviceDataRepository.save(any(TimeDeviceData.class))).thenReturn(timeDeviceData);

    TimeDeviceData result = timeDeviceDataService.addToTimeConverter(converterId, timeDeviceData);

    assertEquals(timeDeviceData, result);
    verify(timeDeviceDataRepository, times(1)).save(timeDeviceData);
  }

  /**
   * Test update time device data.
   */
@Test
  public void testUpdateTimeDeviceData() {
    long id = 1L;
    TimeDeviceData newData = new TimeDeviceData();
    newData.setId(id);
    newData.setDeviceTime(LocalDateTime.now());

    TimeDeviceData existingData = new TimeDeviceData();
    existingData.setId(id);

    when(timeDeviceDataRepository.findById(id)).thenReturn(Optional.of(existingData));
    when(timeDeviceDataRepository.save(any(TimeDeviceData.class))).thenReturn(newData);

    TimeDeviceData result = timeDeviceDataService.updateTimeDeviceData(id, newData);

    assertEquals(newData, result);
    verify(timeDeviceDataRepository, times(1)).findById(id);
    verify(timeDeviceDataRepository, times(1)).save(newData);
  }

  /** Test delete time device data. */
  @Test
  public void testDeleteTimeDeviceData() {
    long id = 1L;

    timeDeviceDataService.deleteTimeDeviceData(id);

    verify(timeDeviceDataRepository, times(1)).deleteById(id);
  }
}
