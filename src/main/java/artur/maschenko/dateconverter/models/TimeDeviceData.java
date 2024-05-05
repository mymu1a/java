package artur.maschenko.dateconverter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

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
