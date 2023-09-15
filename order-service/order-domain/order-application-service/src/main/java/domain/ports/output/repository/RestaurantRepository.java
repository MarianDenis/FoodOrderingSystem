package domain.ports.output.repository;

import order.service.domain.entity.Order;
import order.service.domain.entity.Restaurant;
import order.service.domain.valueObject.TrackingId;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
