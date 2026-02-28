package ee.nimens.restaurant.booking.system.backend.repository;

import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = """
                   SELECT * FROM bookings
                   WHERE starts_at < :end AND ends_at > :start
                   """,
        nativeQuery = true)
    List<BookingEntity> findAllBetween(ZonedDateTime start, ZonedDateTime end);

}
