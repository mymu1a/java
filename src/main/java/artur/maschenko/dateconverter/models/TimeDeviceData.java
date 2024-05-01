package artur.maschenko.dateconverter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/** The type Time device data. */
@Entity
@Table(name = "time_device_data")
@Data
public class TimeDeviceData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime deviceTime;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnore
  @JoinColumn(name = "time_converter_id")
  private TimeConverter timeConverter;
}
