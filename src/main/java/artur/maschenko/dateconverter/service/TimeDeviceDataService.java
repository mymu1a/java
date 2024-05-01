package artur.maschenko.dateconverter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/** The type Time device data service. */
@Service
public class TimeDeviceDataService {
  private static final Logger logger = LoggerFactory.getLogger(TimeDeviceDataService.class);
  private final TimeDeviceDataRepository timeDeviceDataRepository;

  /**
   * Instantiates a new Time device data service.
   *
   * @param timeDeviceDataRepository the time device data repository
   */
  @Autowired
  public TimeDeviceDataService(TimeDeviceDataRepository timeDeviceDataRepository) {
    this.timeDeviceDataRepository = timeDeviceDataRepository;
  }

  /**
   * Gets all time device data.
   *
   * @return the all time device data
   */
  public List<TimeDeviceData> getAllTimeDeviceData() {
    logger.info("Getting all time device data");
    return timeDeviceDataRepository.findAll();
  }

  /**
   * Gets time device data by id.
   *
   * @param id the id
   * @return the time device data by id
   */
  public TimeDeviceData getTimeDeviceDataById(Long id) {
    logger.info("Getting time device data by ID: {}", id);
    return timeDeviceDataRepository
        .findById(id)
        .orElseThrow(() -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));
  }

  /**
   * Save time device data time device data.
   *
   * @param timeDeviceData the time device data
   * @return the time device data
   */
  public TimeDeviceData saveTimeDeviceData(TimeDeviceData timeDeviceData) {
    logger.info("Saving time device data: {}", timeDeviceData);
    return timeDeviceDataRepository.save(timeDeviceData);
  }

  /**
   * Add to time converter time device data.
   *
   * @param timeConverterId the time converter id
   * @param timeDeviceData the time device data
   * @return the time device data
   */
  public TimeDeviceData addToTimeConverter(Long timeConverterId, TimeDeviceData timeDeviceData) {
    logger.info(
        "Adding time device data to converter with ID {}: {}", timeConverterId, timeDeviceData);
    TimeConverter timeConverter = new TimeConverter();
    timeConverter.setId(timeConverterId);
    timeDeviceData.setTimeConverter(timeConverter);
    return timeDeviceDataRepository.save(timeDeviceData);
  }

  /**
   * Update time device data time device data.
   *
   * @param id the id
   * @param newData the new data
   * @return the time device data
   */
  public TimeDeviceData updateTimeDeviceData(Long id, TimeDeviceData newData) {
    logger.info("Updating time device data with ID {}: {}", id, newData);
    TimeDeviceData existingData =
        timeDeviceDataRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));

    existingData.setDeviceTime(newData.getDeviceTime());
    return timeDeviceDataRepository.save(existingData);
  }

  /**
   * Delete time device data.
   *
   * @param id the id
   */
  public void deleteTimeDeviceData(Long id) {
    logger.info("Deleting time device data with ID: {}", id);
    timeDeviceDataRepository.deleteById(id);
  }
}
