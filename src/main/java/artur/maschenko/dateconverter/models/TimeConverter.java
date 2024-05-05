package artur.maschenko.dateconverter.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

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
