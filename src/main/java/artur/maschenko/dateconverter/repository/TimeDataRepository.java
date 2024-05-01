package artur.maschenko.dateconverter.repository;

import artur.maschenko.dateconverter.models.TimeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** The interface Time data repository. */
@Repository
public interface TimeDataRepository extends JpaRepository<TimeData, Long> {
  /**
   * Find max milliseconds long.
   *
   * @return the long
   */
  @Query("SELECT MAX(td.milliseconds) FROM TimeData td")
  Long findMaxMilliseconds();
}
