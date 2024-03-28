package artur.maschenko.dateconverter.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TimeConverter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime localTime;

    private LocalDateTime gmtTime;

    @ManyToMany(mappedBy = "manyToManyTimeConverters")
    private List<TimeData> manyToManyTimeDataList;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalDateTime localTime) {
        this.localTime = localTime;
    }

    public LocalDateTime getGmtTime() {
        return gmtTime;
    }

    public void setGmtTime(LocalDateTime gmtTime) {
        this.gmtTime = gmtTime;
    }

    public List<TimeData> getManyToManyTimeDataList() {
        return manyToManyTimeDataList;
    }

    public void setManyToManyTimeDataList(List<TimeData> manyToManyTimeDataList) {
        this.manyToManyTimeDataList = manyToManyTimeDataList;
    }
}