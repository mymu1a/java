package artur.maschenko.dateconverter.repository;

import artur.maschenko.dateconverter.models.TimeDataConverterMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeDataConverterMappingRepository extends JpaRepository<TimeDataConverterMapping, Long> {
}