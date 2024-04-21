package artur.maschenko.dateconverter.controllers;

import artur.maschenko.dateconverter.models.TimeConverter;
import artur.maschenko.dateconverter.service.TimeConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time-converter")
public class TimeConverterController {
    private final TimeConverterService timeConverterService;

    @Autowired
    public TimeConverterController(TimeConverterService timeConverterService) {
        this.timeConverterService = timeConverterService;
    }

    @GetMapping("/")
    public List<TimeConverter> getAllTimeConverters() {
        return timeConverterService.getAllTimeConverters();
    }

    @GetMapping("/{id}")
    public Optional<TimeConverter> getTimeConverterById(@PathVariable Long id) {
        return timeConverterService.getTimeConverterById(id);
    }

    @PostMapping("/")
    public TimeConverter saveTimeConverter(@RequestBody TimeConverter timeConverter) {
        return timeConverterService.saveTimeConverter(timeConverter);
    }

    @PutMapping("/{id}")
    public TimeConverter updateTimeConverter(@PathVariable Long id, @RequestBody TimeConverter timeConverter) {
        timeConverter.setId(id);
        return timeConverterService.saveTimeConverter(timeConverter);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeConverter(@PathVariable Long id) {
        timeConverterService.deleteTimeConverter(id);
    }
}
