package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** The type Time converter service. */
@Service
public class TimeConverterService {
  private final TimeConverterRepository timeConverterRepository;
  private static final Logger logger = LoggerFactory.getLogger(TimeConverterService.class);

  /**
   * Instantiates a new Time converter service.
   *
   * @param timeConverterRepository the time converter repository
   */
  @Autowired
  public TimeConverterService(TimeConverterRepository timeConverterRepository) {
    this.timeConverterRepository = timeConverterRepository;
  }

  /**
   * Gets all time converters.
   *
   * @return the all time converters
   */
  public List<TimeConverter> getAllTimeConverters() {
    logger.info("Getting all time converters");
    return timeConverterRepository.findAll();
  }

  /**
   * Gets time converter by id.
   *
   * @param id the id
   * @return the time converter by id
   */
  public Optional<TimeConverter> getTimeConverterById(Long id) {
    logger.info("Getting time converter by ID: {}", id);
    return timeConverterRepository.findById(id);
  }

  /**
   * Save time converter time converter.
   *
   * @param timeConverter the time converter
   * @return the time converter
   */
  public TimeConverter saveTimeConverter(TimeConverter timeConverter) {
    logger.info("Saving time converter: {}", timeConverter);
    return timeConverterRepository.save(timeConverter);
  }

  /**
   * Update time converter time converter.
   *
   * @param id the id
   * @param timeConverter the time converter
   * @return the time converter
   */
  public TimeConverter updateTimeConverter(Long id, TimeConverter timeConverter) {
    logger.info("Updating time converter with ID {}: {}", id, timeConverter);
    timeConverter.setId(id);
    return timeConverterRepository.save(timeConverter);
  }

  /**
   * Delete time converter.
   *
   * @param id the id
   */
  public void deleteTimeConverter(Long id) {
    logger.info("Deleting time converter with ID: {}", id);
    timeConverterRepository.deleteById(id);
  }
}
