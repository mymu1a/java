package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.models.TimeDeviceData;
import artur.maschenko.dateconverter.repository.TimeDeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TimeDeviceDataService {
    private final TimeDeviceDataRepository timeDeviceDataRepository;

    @Autowired
    public TimeDeviceDataService(TimeDeviceDataRepository timeDeviceDataRepository) {
        this.timeDeviceDataRepository = timeDeviceDataRepository;
    }

    public List<TimeDeviceData> getAllTimeDeviceData() {
        return timeDeviceDataRepository.findAll();
    }

    public TimeDeviceData getTimeDeviceDataById(Long id) {
        return timeDeviceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));
    }

    public TimeDeviceData saveTimeDeviceData(TimeDeviceData timeDeviceData) {
        return timeDeviceDataRepository.save(timeDeviceData);
    }

    public TimeDeviceData addToTimeConverter(Long timeConverterId, TimeDeviceData timeDeviceData) {
        TimeConverter timeConverter = new TimeConverter();
        timeConverter.setId(timeConverterId);
        timeDeviceData.setTimeConverter(timeConverter);
        return timeDeviceDataRepository.save(timeDeviceData);
    }

    public TimeDeviceData updateTimeDeviceData(Long id, TimeDeviceData newData) {
        TimeDeviceData existingData = timeDeviceDataRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TimeDeviceData not found with id: " + id));

        existingData.setDeviceTime(newData.getDeviceTime());
        return timeDeviceDataRepository.save(existingData);
    }

    public void deleteTimeDeviceData(Long id) {
        timeDeviceDataRepository.deleteById(id);
    }
}
