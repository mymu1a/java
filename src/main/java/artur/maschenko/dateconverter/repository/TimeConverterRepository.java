package artur.maschenko.dateconverter.repository;

import artur.maschenko.dateconverter.models.TimeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeConverterRepository extends JpaRepository<TimeConverter, Long> {
}