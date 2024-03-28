package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeDataConverterMapping;
import artur.maschenko.dateconverter.repository.TimeDataConverterMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeDataConverterMappingService {
    private final TimeDataConverterMappingRepository timeDataConverterMappingRepository;

    @Autowired
    public TimeDataConverterMappingService(TimeDataConverterMappingRepository timeDataConverterMappingRepository) {
        this.timeDataConverterMappingRepository = timeDataConverterMappingRepository;
    }

    // Получить все записи из таблицы TimeDataConverterMapping
    public List<TimeDataConverterMapping> getAllTimeDataConverterMappings() {
        return timeDataConverterMappingRepository.findAll();
    }

    // Получить запись из таблицы TimeDataConverterMapping по идентификатору
    public Optional<TimeDataConverterMapping> getTimeDataConverterMappingById(Long id) {
        return timeDataConverterMappingRepository.findById(id);
    }

    // Сохранить или обновить запись в таблице TimeDataConverterMapping
    public TimeDataConverterMapping saveTimeDataConverterMapping(TimeDataConverterMapping timeDataConverterMapping) {
        return timeDataConverterMappingRepository.save(timeDataConverterMapping);
    }

    // Удалить запись из таблицы TimeDataConverterMapping по идентификатору
    public void deleteTimeDataConverterMapping(Long id) {
        timeDataConverterMappingRepository.deleteById(id);
    }
}