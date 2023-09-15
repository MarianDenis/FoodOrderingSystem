package domain.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import valueObject.OrderStatus;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NonNull
    private final UUID orderTrackingId;

    @NonNull
    private final OrderStatus orderStatus;

    @NotNull
    private final String message;
}
