package domain.ports.output.publisher.payment;

import event.publisher.DomainEventPublisher;
import order.service.domain.event.OrderCancelledEvent;
import order.service.domain.event.OrderCreatedEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
