package by.pohodsky.bsbtask.service;

import by.pohodsky.bsbtask.entity.AppUser;
import by.pohodsky.bsbtask.repository.AppUserRepository;
import by.pohodsky.bsbtask.service.enums.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    public static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    AppUserRepository appUserRepository;


    public boolean createNewAppUser(AppUser appUser) {

        logger.info("Create new user:{}", appUser);

        if (appUserRepository.findById(appUser.getId()).isPresent()) {
            logger.info("This user exists");
            return false;
        }
        if (appUserRepository.findAppUserByEmail(appUser.getEmail()) != null) {
            logger.info("This user with this email exists");
            return false;
        }
        if (appUserRepository.findAppUserByPhoneNumber(appUser.getPhoneNumber()) != null) {
            logger.info("This user with this phone number exists");
            return false;
        }
        appUser.setStatus(-1);
        appUserRepository.save(appUser);
        return true;
    }

    public AppUser updateAppUserStatus(String searchString, String type) {

        AppUser appUser = appUserRepository.findAppUserById(searchString);
        if (appUser == null) appUser = appUserRepository.findAppUserByEmail(searchString);
        if (appUser == null) appUser = appUserRepository.findAppUserByPhoneNumber(searchString);

        switch (type) {
            case "PLATINUM": {
                setStatusAppUser(appUser, Types.PLATINUM.ordinal());
                break;
            }
            case "GOLD": {
                setStatusAppUser(appUser, Types.GOLD.ordinal());
                break;
            }
            case "CLASSIC": {
                setStatusAppUser(appUser, Types.CLASSIC.ordinal());
                break;
            }
            default: {
                setStatusAppUser(appUser, -1);
                break;
            }
        }

        return appUserRepository.save(appUser);
    }

    private void setStatusAppUser(AppUser appUser, int statusAppUser) {
        appUser.setStatus(statusAppUser);
    }

}


