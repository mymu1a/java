package artur.maschenko.dateconverter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TimeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long milliseconds;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "time_data_converter_mapping",
            joinColumns = @JoinColumn(name = "time_data_id"),
            inverseJoinColumns = @JoinColumn(name = "time_converter_id")
    )
    private List<TimeConverter> manyToManyTimeConverters;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public List<TimeConverter> getManyToManyTimeConverters() {
        return manyToManyTimeConverters;
    }

    public void setManyToManyTimeConverters(List<TimeConverter> manyToManyTimeConverters) {
        this.manyToManyTimeConverters = manyToManyTimeConverters;
    }
}