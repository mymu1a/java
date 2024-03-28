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

    // Получить все записи из таблицы TimeConverter
    public List<TimeConverter> getAllTimeConverters() {
        return timeConverterRepository.findAll();
    }

    // Получить запись из таблицы TimeConverter по идентификатору
    public Optional<TimeConverter> getTimeConverterById(Long id) {
        return timeConverterRepository.findById(id);
    }

    // Сохранить или обновить запись в таблице TimeConverter
    public TimeConverter saveTimeConverter(TimeConverter timeConverter) {
        return timeConverterRepository.save(timeConverter);
    }

    // Удалить запись из таблицы TimeConverter по идентификатору
    public void deleteTimeConverter(Long id) {
        timeConverterRepository.deleteById(id);
    }
}