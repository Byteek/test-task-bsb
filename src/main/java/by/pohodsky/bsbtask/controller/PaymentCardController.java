package by.pohodsky.bsbtask.controller;

import by.pohodsky.bsbtask.entity.PaymentCard;
import by.pohodsky.bsbtask.service.PaymentCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentCardController {

    @Autowired
    PaymentCardService paymentCardService;

    @GetMapping(params = {"page", "size"}, path = "/findPhoneNumberList")
    public List<String> findPaginatedPhoneNumber(@RequestParam("page") int page,
                                                 @RequestParam("size") int size,
                                                 @RequestParam String cardType,
                                                 @RequestParam String currency) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "linkedAppClientPhoneNumber");
        Page<PaymentCard> resultPage = paymentCardService.findPaginatePaymentCardByCurrencyAndCardType(currency, cardType, pageable);
        List<String> phoneNumbers = new ArrayList<>(size);
        resultPage.getContent()
                .forEach(
                        paymentCard -> phoneNumbers.add(paymentCard.getLinkedAppClientPhoneNumber()));
        return phoneNumbers;
    }

    @PostMapping("/user/createPaymentCard")
    public ResponseEntity createNewPaymentCard(
            @RequestParam String cardNumber,
            @RequestParam String currency,
            @RequestParam String linkedAppClientPhoneNumber,
            @RequestParam String type
    ) {
        PaymentCard paymentCard = PaymentCard.builder()
                .cardNumber(cardNumber)
                .currency(currency)
                .linkedAppClientPhoneNumber(linkedAppClientPhoneNumber)
                .type(type)
                .build();

        if (paymentCardService.createPaymentCard(paymentCard)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
