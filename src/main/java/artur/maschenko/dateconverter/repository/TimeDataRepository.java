package artur.maschenko.dateconverter.repository;

import artur.maschenko.dateconverter.models.TimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDataRepository extends JpaRepository<TimeData, Long> {
    @Query("SELECT MAX(td.milliseconds) FROM TimeData td")
    Long findMaxMilliseconds();
}