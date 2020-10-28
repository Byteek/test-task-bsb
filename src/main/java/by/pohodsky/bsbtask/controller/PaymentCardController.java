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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentCardController {

    @Autowired
    PaymentCardService paymentCardService;


    @GetMapping(params = {"page", "size"}, path = "/phoneNumberList")
    public List<String> findPaginated(@RequestParam("page") int page,
                                      @RequestParam("size") int size,
                                      @RequestParam String cardType,
                                      @RequestParam String currency) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "linkedAppUserNumberPhone");
        Page<PaymentCard> resultPage = paymentCardService.findPaginatePaymentCardByCurrencyAndCardType(currency, cardType, pageable);
        List<String> phoneNumbers = new ArrayList<>(size);
        resultPage.getContent()
                .forEach(
                        paymentCard -> phoneNumbers.add(paymentCard.getLinkedAppUserNumberPhone()));
        return phoneNumbers;
    }

    @PostMapping("/newPaymentCard")
    public ResponseEntity createNewPaymentCard(@RequestBody PaymentCard paymentCard) {

        if (paymentCardService.createPaymentCard(paymentCard)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
