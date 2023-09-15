package domain.ports.output.repository;

import order.service.domain.entity.Customer;
import order.service.domain.entity.Order;
import order.service.domain.entity.Restaurant;
import order.service.domain.valueObject.TrackingId;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

    Optional<Customer> findCustomer(UUID customerId);
}
