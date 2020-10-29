package by.pohodsky.bsbtask.service;

import by.pohodsky.bsbtask.entity.AppClient;
import by.pohodsky.bsbtask.entity.PaymentCard;
import by.pohodsky.bsbtask.repository.PaymentCardRepository;
import by.pohodsky.bsbtask.service.enums.Currencies;
import by.pohodsky.bsbtask.service.enums.Types;
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
    AppClientService appClientService;

    public Page<PaymentCard> findPaginatePaymentCardByCurrencyAndCardType(String currency, String cardType, Pageable pageable) {
        return paymentCardRepository.findAllByCurrencyAndType(currency, cardType, pageable);
    }

    public boolean createPaymentCard(PaymentCard paymentCard) {

        logger.info("Validated new payment card: {}", paymentCard);
        paymentCard.setType(paymentCard.getType().strip().toUpperCase());
        paymentCard.setCurrency(paymentCard.getCurrency().strip().toUpperCase());

        if (validateCurrencyAndType(paymentCard)) {

            if (paymentCardRepository.findByCardNumber(paymentCard.getCardNumber()) != null) {
                return false;
            }
            if (appClientService.findAppClientByPhoneNumber(paymentCard.getLinkedAppClientPhoneNumber()) == null) {
                return false;
            }


            AppClient appClient = appClientService.updateAppClientStatus(paymentCard.getLinkedAppClientPhoneNumber(), paymentCard.getType());
            paymentCard.setLinkedAppClientPhoneNumber(appClient.getPhoneNumber());
            paymentCardRepository.save(paymentCard);
            logger.info("Create new payment card: {}", paymentCard);

        } else {
            logger.info("No valid");
            return false;
        }
        logger.info("Valid");
        return true;
    }


    private boolean validateCurrencyAndType(PaymentCard paymentCard) {
        int counterCurrencies = 0;
        int counterTypes = 0;
        for (int i = 0; i < Currencies.values().length; i++) {
            if (paymentCard.getCurrency().equals(Currencies.values()[i].name())) {
                counterCurrencies++;
            }
        }
        for (int i = 0; i < Types.values().length; i++) {
            if (paymentCard.getType().equals(Types.values()[i].name())) {
                counterTypes++;
            }
        }
        return counterCurrencies != 0 && counterTypes != 0;
    }

}
