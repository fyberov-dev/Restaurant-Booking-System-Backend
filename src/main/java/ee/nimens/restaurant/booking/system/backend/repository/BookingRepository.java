package ee.nimens.restaurant.booking.system.backend.repository;

import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findAllByStartsAtBetween(ZonedDateTime after, ZonedDateTime before);

}
