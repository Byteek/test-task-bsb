package by.pohodsky.bsbtask.service;

import by.pohodsky.bsbtask.entity.AppUser;
import by.pohodsky.bsbtask.entity.PaymentCard;
import by.pohodsky.bsbtask.repository.PaymentCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PaymentCardService {

    public static final Logger logger = LoggerFactory.getLogger(PaymentCardService.class);

    @Autowired
    PaymentCardRepository paymentCardRepository;

    @Autowired
    AppUserService appUserService;

    public boolean createPaymentCard(PaymentCard paymentCard) {
        logger.info("Create new payment card: {}", paymentCard);

        paymentCard.setType(paymentCard.getType().strip().toUpperCase());
        paymentCard.setCurrency(paymentCard.getCurrency().strip().toUpperCase());

        AppUser appUser = appUserService.updateAppUserStatus(paymentCard.getLinkedAppUserNumberPhone(), paymentCard.getType());
        paymentCard.setLinkedAppUserNumberPhone(appUser.getPhoneNumber());
        paymentCardRepository.save(paymentCard);
        return true;
    }

    public Page<PaymentCard> findPaginatePaymentCardByCurrencyAndCardType(String currency, String cardType, Pageable pageable) {
        return paymentCardRepository.findAllByCurrencyAndType(currency, cardType, pageable);
    }

}
