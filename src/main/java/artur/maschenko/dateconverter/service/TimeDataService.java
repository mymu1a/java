package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/** The type Time data service. */
@Service
public class TimeDataService {
  private static final Logger logger = LoggerFactory.getLogger(TimeDataService.class);
  private final TimeDataRepository timeDataRepository;

  /**
   * Instantiates a new Time data service.
   *
   * @param timeDataRepository the time data repository
   */
  @Autowired
  public TimeDataService(TimeDataRepository timeDataRepository) {
    this.timeDataRepository = timeDataRepository;
  }

  /**
   * Gets all time data.
   *
   * @return the all time data
   */
  @Cacheable("timeData")
  public List<TimeData> getAllTimeData() {
    logger.info("Getting all time data");
    return timeDataRepository.findAll();
  }

  /**
   * Gets time data by id.
   *
   * @param id the id
   * @return the time data by id
   */
  @Cacheable("timeData")
  public Optional<TimeData> getTimeDataById(Long id) {
    logger.info("Getting time data by ID: {}", id);
    return timeDataRepository.findById(id);
  }

  /**
   * Save time data time data.
   *
   * @param timeData the time data
   * @return the time data
   */
  @CacheEvict(value = "timeData", allEntries = true)
  public TimeData saveTimeData(TimeData timeData) {
    logger.info("Saving time data: {}", timeData);
    return timeDataRepository.save(timeData);
  }

  /**
   * Update time data time data.
   *
   * @param id the id
   * @param timeData the time data
   * @return the time data
   */
  @CacheEvict(value = "timeData", allEntries = true)
  public TimeData updateTimeData(Long id, TimeData timeData) {
    logger.info("Updating time data with ID {}: {}", id, timeData);
    timeData.setId(id);
    return timeDataRepository.save(timeData);
  }

  /**
   * Delete time data.
   *
   * @param id the id
   */
  @CacheEvict(value = "timeData", allEntries = true)
  public void deleteTimeData(Long id) {
    logger.info("Deleting time data with ID: {}", id);
    timeDataRepository.deleteById(id);
  }

  /**
   * Gets max milliseconds.
   *
   * @return the max milliseconds
   */
  @Cacheable("maxMilliseconds")
  public Long getMaxMilliseconds() {
    logger.info("Getting max milliseconds");
    return timeDataRepository.findMaxMilliseconds();
  }
}
