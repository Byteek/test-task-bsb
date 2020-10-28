package by.pohodsky.bsbtask.controller;

import by.pohodsky.bsbtask.entity.AppClient;
import by.pohodsky.bsbtask.service.AppClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AppClientService appClientService;

    @PostMapping("/newUser")
    public ResponseEntity createNewAppUser(@RequestBody AppClient appClient) {

        if (appClientService.createNewAppUser(appClient)) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
 