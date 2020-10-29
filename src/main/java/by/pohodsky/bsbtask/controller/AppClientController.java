package by.pohodsky.bsbtask.controller;

import by.pohodsky.bsbtask.entity.AppClient;
import by.pohodsky.bsbtask.service.AppClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "App client controller")
@RestController
public class AppClientController {

    @Autowired
    AppClientService appClientService;

    @ApiOperation(value = "The method of placing a client in the system.", notes = messageInAppClientController)
    @PostMapping("/user/createAppClient")
    public ResponseEntity createNewAppClient(@RequestBody @Validated AppClient appClient) {

        if (appClientService.createNewAppClient(appClient)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    private static final String messageInAppClientController = "The client must have a first and last name (patronymic is optional), email and phone number.\n" +
            "\n" +
            "The client is automatically assigned the status -1, because he doesn't have a map yet.";
}
 