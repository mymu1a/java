package artur.maschenko.dateconverter.models;

import jakarta.persistence.*;

@Entity
@Table(name = "time_data_converter_mapping")
public class TimeDataConverterMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "time_data_id")
    private TimeData timeData;

    @ManyToOne
    @JoinColumn(name = "time_converter_id")
    private TimeConverter timeConverter;

    // геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeData getTimeData() {
        return timeData;
    }

    public void setTimeData(TimeData timeData) {
        this.timeData = timeData;
    }

    public TimeConverter getTimeConverter() {
        return timeConverter;
    }

    public void setTimeConverter(TimeConverter timeConverter) {
        this.timeConverter = timeConverter;
    }
}