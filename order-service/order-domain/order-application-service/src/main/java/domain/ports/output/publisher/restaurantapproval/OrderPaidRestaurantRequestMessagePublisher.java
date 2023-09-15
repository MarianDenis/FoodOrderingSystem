package domain.ports.output.publisher.restaurantapproval;

import event.publisher.DomainEventPublisher;
import order.service.domain.event.OrderCancelledEvent;
import order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
