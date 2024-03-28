package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.models.TimeData;
import artur.maschenko.dateconverter.repository.TimeDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeDataService {
    @Autowired
    private TimeDataRepository timeDataRepository;

    public List<TimeData> getAllTimeData() {
        // Получить все записи из таблицы TimeData
        return timeDataRepository.findAll();
    }

    public Optional<TimeData> getTimeDataById(Long id) {
        // Получить запись из таблицы TimeData по идентификатору
        return timeDataRepository.findById(id);
    }

    public TimeData saveTimeData(TimeData timeData) {
        // Сохранить или обновить запись в таблице TimeData
        return timeDataRepository.save(timeData);
    }

    public void deleteTimeData(Long id) {
        // Удалить запись из таблицы TimeData по идентификатору
        timeDataRepository.deleteById(id);
    }
}