package domain;

import domain.dto.create.CreateOrderCommand;
import domain.dto.create.CreateOrderResponse;
import domain.dto.track.TrackOrderQuery;
import domain.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderTrackCommandHandler {

    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }

}
