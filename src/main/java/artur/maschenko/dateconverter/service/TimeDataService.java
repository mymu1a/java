package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeDataService {
    private final TimeDataRepository timeDataRepository;

    @Autowired
    public TimeDataService(TimeDataRepository timeDataRepository) {
        this.timeDataRepository = timeDataRepository;
    }

    @Cacheable("timeData")
    public List<TimeData> getAllTimeData() {
        return timeDataRepository.findAll();
    }

    @Cacheable("timeData")
    public Optional<TimeData> getTimeDataById(Long id) {
        return timeDataRepository.findById(id);
    }

    @CacheEvict(value = "timeData", allEntries = true)
    public TimeData saveTimeData(TimeData timeData) {
        return timeDataRepository.save(timeData);
    }

    @CacheEvict(value = "timeData", allEntries = true)
    public TimeData updateTimeData(Long id, TimeData timeData) {
        timeData.setId(id);
        return timeDataRepository.save(timeData);
    }

    @CacheEvict(value = "timeData", allEntries = true)
    public void deleteTimeData(Long id) {
        timeDataRepository.deleteById(id);
    }

    @Cacheable("maxMilliseconds")
    public Long getMaxMilliseconds() {
        return timeDataRepository.findMaxMilliseconds();
    }}
