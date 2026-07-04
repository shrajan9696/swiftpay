package transactiongateway.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import transactiongateway.dto.PaymentRequest;
import transactiongateway.dto.PaymentResponse;
import transactiongateway.entity.Payments;
import transactiongateway.exception.ErrorResponse;
import transactiongateway.service.PaymentService;
import java.util.List;

@Tag(name = "Payments", description = "Payment APIs")
@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(
            summary = "Create Payment",
            description = "Creates a new payment and publishes PaymentInitiated event."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Payment Accepted", content = @Content(
                    schema = @Schema(implementation = PaymentResponse.class)
            )),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )),
            @ApiResponse(responseCode = "409", description = "Duplicate Transaction", content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            ))
    })
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response = paymentService.createPayment(request);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @Operation(summary = "Transaction History")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payments>> getTransactions(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                paymentService.getTransactionHistory(userId)
        );
    }
}