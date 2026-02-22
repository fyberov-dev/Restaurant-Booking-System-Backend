package ee.nimens.restaraunt.booking.system.backend.repository;

import ee.nimens.restaraunt.booking.system.backend.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
