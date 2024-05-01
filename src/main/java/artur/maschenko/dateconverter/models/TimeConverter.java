package artur.maschenko.dateconverter.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/** The type Time converter. */
@Entity
@Data
public class TimeConverter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime localTime;

  private LocalDateTime gmtTime;

  @OneToMany(mappedBy = "timeConverter", cascade = CascadeType.ALL)
  private List<TimeDeviceData> timeDeviceDataList;
}
