package artur.maschenko.dateconverter.repository;

import artur.maschenko.dateconverter.models.TimeDeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** The interface Time device data repository. */
@Repository
public interface TimeDeviceDataRepository extends JpaRepository<TimeDeviceData, Long> {}
