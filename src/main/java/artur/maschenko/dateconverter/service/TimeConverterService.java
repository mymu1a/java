package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.repository.TimeConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeConverterService {
    private final TimeConverterRepository timeConverterRepository;

    @Autowired
    public TimeConverterService(TimeConverterRepository timeConverterRepository) {
        this.timeConverterRepository = timeConverterRepository;
    }

    public List<TimeConverter> getAllTimeConverters() {
        return timeConverterRepository.findAll();
    }

    public Optional<TimeConverter> getTimeConverterById(Long id) {
        return timeConverterRepository.findById(id);
    }

    public TimeConverter saveTimeConverter(TimeConverter timeConverter) {
        return timeConverterRepository.save(timeConverter);
    }

    public TimeConverter updateTimeConverter(Long id, TimeConverter timeConverter) {
        timeConverter.setId(id);
        return timeConverterRepository.save(timeConverter);
    }

    public void deleteTimeConverter(Long id) {
        timeConverterRepository.deleteById(id);
    }
}
