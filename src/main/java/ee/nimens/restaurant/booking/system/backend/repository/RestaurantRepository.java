package ee.nimens.restaurant.booking.system.backend.repository;

import ee.nimens.restaurant.booking.system.backend.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
}
