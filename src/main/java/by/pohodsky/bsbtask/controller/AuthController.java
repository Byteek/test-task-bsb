package by.pohodsky.bsbtask.controller;


import by.pohodsky.bsbtask.entity.AppUser;
import by.pohodsky.bsbtask.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api( tags = "Authentication controller" )
@RestController
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @ApiOperation(value = "Method for registering a system user",
            notes = "")
    @PostMapping("/register")
    public String registerUser(@RequestParam String login,
                               @RequestParam String password) {

        AppUser appUser = AppUser.builder()
                .login(login)
                .password(password)
                .build();
        appUser.setLogin(login);
        appUser.setPassword(password);
        appUserService.saveUser(appUser);
        return "OK";
    }

}