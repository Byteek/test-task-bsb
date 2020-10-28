package by.pohodsky.bsbtask.repository;

import by.pohodsky.bsbtask.entity.PaymentCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, String> {

    Page<PaymentCard> findAllByCurrencyAndType(String currency, String type, Pageable pageable);

}
