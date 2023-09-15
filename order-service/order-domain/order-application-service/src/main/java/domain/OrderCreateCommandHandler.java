package domain;

import domain.dto.create.CreateOrderCommand;
import domain.dto.create.CreateOrderResponse;
import domain.dto.track.TrackOrderQuery;
import domain.dto.track.TrackOrderResponse;
import domain.mapper.OrderDataMapper;
import domain.ports.input.service.OrderApplicationService;
import domain.ports.output.repository.CustomerRepository;
import domain.ports.output.repository.OrderRepository;
import domain.ports.output.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import order.service.domain.OrderDomainService;
import order.service.domain.entity.Customer;
import order.service.domain.entity.Order;
import order.service.domain.entity.Restaurant;
import order.service.domain.event.OrderCreatedEvent;
import order.service.domain.exception.OrderDomainException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderDomainService orderDomainService,
                                     OrderRepository orderRepository,
                                     CustomerRepository customerRepository,
                                     RestaurantRepository restaurantRepository,
                                     OrderDataMapper orderDataMapper) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order orderResult = saveOrder(order);
        log.info("Created order");
        return orderDataMapper.orderToCreateOrderResponse(orderResult, "Message");

    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            throw new OrderDomainException("CANNOT SAVE order");
        }
        return orderResult;
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> restaurantInformation = restaurantRepository.findRestaurantInformation(restaurant);
        if (restaurantInformation.isEmpty()) {
            throw new OrderDomainException("Cannot find restaurant");
        }
        return restaurantInformation.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if(customer.isEmpty()) {
            log.warn("Cannot find customer with id: {}", customerId);
            throw new OrderDomainException("Customer not existing");
        }
    }

}
