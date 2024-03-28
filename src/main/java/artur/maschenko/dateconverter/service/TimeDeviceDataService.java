package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeDeviceDataService {
    private final TimeDeviceDataRepository timeDeviceDataRepository;

    @Autowired
    public TimeDeviceDataService(TimeDeviceDataRepository timeDeviceDataRepository) {
        this.timeDeviceDataRepository = timeDeviceDataRepository;
    }

    @Transactional(readOnly = true)
    public TimeDeviceData getTimeDeviceDataById(Long id) {
        return timeDeviceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<TimeDeviceData> getAllTimeDeviceData() {
        return timeDeviceDataRepository.findAll();
    }

    @Transactional
    public TimeDeviceData saveTimeDeviceData(TimeDeviceData timeDeviceData) {
        return timeDeviceDataRepository.save(timeDeviceData);
    }

    @Transactional
    public TimeDeviceData updateTimeDeviceData(Long id, TimeDeviceData newData) {
        TimeDeviceData existingData = timeDeviceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));

        existingData.setDeviceTime(newData.getDeviceTime());
        existingData.setTimeData(newData.getTimeData());

        return timeDeviceDataRepository.save(existingData);
    }

    @Transactional
    public void deleteTimeDeviceData(Long id) {
        timeDeviceDataRepository.deleteById(id);
    }
}