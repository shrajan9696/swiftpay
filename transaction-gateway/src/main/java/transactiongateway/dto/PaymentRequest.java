package transactiongateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @Schema(
            description = "Unique transaction id",
            example = "TXN5001"
    )
    @NotBlank(message = "Transaction Id is required")
    private String transactionId;

    @Schema(
            description = "Sender Account Id",
            example = "101"
    )
    @NotNull(message = "Sender Id is required")
    @Positive(message = "Sender Id must be positive")
    private Long senderId;

    @Schema(
            description = "Receiver Account Id",
            example = "102"
    )
    @NotNull(message = "Receiver Id is required")
    @Positive(message = "Receiver Id must be positive")
    private Long receiverId;

    @Schema(
            description = "Transfer amount",
            example = "250"
    )
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @Schema(
            description = "Currency",
            example = "INR"
    )
    @NotBlank(message = "Currency is required")
    @Size(min = 3, max = 3, message = "Currency must be a 3-letter ISO code")
    private String currency;
}