package ee.nimens.restaurant.booking.system.backend.repository;

import ee.nimens.restaurant.booking.system.backend.entity.BookingEntity;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = """
                   SELECT * FROM bookings
                   WHERE starts_at <= :end AND ends_at >= :start
                   """,
        nativeQuery = true)
    List<BookingEntity> findAllBetween(Instant start, Instant end);

    @Query(value = """
                   SELECT * FROM bookings
                   WHERE table_id = :tableId AND starts_at <= :end AND ends_at >= :start
                   """,
        nativeQuery = true)
    Optional<BookingEntity> findTableBookingsBetween(Long tableId, Instant start, Instant end);

}
