package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.service.DateConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DateConverterController {

    private final DateConverterService dateConverterService;

    @Autowired
    public DateConverterController(DateConverterService dateConverterService) {
        this.dateConverterService = dateConverterService;
    }

    @GetMapping("/date-convert")
    public Map<String, String> convertTime(@RequestParam long milliseconds) {
        return dateConverterService.convertTime(milliseconds);
    }
}