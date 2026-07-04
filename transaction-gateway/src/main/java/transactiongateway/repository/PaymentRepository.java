package transactiongateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import transactiongateway.entity.Payments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payments, UUID> {

    boolean existsByTransactionId(String transactionId);

    Optional<Payments> findByTransactionId(String transactionId);

    List<Payments> findBySenderIdOrReceiverIdOrderByCreatedAtDesc(
            Long senderId,
            Long receiverId
    );


}