package by.pohodsky.bsbtask.controller;

import by.pohodsky.bsbtask.entity.PaymentCard;
import by.pohodsky.bsbtask.service.PaymentCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "Payment card controller")
@RestController
public class PaymentCardController {

    @Autowired
    PaymentCardService paymentCardService;

    @ApiOperation(value = "Page by page search for phone numbers",
            notes = messageInPaymentCardControllerFromFindPaginatedPhoneNumber)
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


    @ApiOperation(value = "Method of issuing a card and binding to a specific client",
            notes = messageInPaymentCardControllerFromCreateNewPaymentCard)
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


    private static final String messageInPaymentCardControllerFromFindPaginatedPhoneNumber = "A method that returns a page-by-page list of phone numbers depending on the specified parameters (number of records, page number, card type, currency).";

    private static final String messageInPaymentCardControllerFromCreateNewPaymentCard = "Based on the selected type of card, the client is assigned the appropriate status: Classic - 0, Gold - 1, Platinum - 2\n" +
            "\n" +
            "The card is linked to the customer's phone number, so the customer's phone number existing in the database is entered.\n" +
            "There are three currency options: BYN, USD, EUR and three client status options: Classic, Gold and Platinum. The server only allows these values";

}
