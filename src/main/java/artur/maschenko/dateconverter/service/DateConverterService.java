package artur.maschenko.dateconverter.service;

import artur.maschenko.dateconverter.program.DateConverterProgram;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DateConverterService {

    private final DateConverterProgram dateConverterProgram;//нельзя поменять потом

    //конструктор
    public DateConverterService(DateConverterProgram dateConverterProgram) {
        this.dateConverterProgram = dateConverterProgram;
    }

    //передает в програм
    public Map<String, String> convertTime(long milliseconds) {
        return dateConverterProgram.convertTime(milliseconds);
    }
}