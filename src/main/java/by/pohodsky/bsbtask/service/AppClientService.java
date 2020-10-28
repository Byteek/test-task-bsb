package by.pohodsky.bsbtask.service;

import by.pohodsky.bsbtask.entity.AppClient;
import by.pohodsky.bsbtask.repository.AppClientRepository;
import by.pohodsky.bsbtask.service.enums.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppClientService {

    public static final Logger logger = LoggerFactory.getLogger(AppClientService.class);

    @Autowired
    AppClientRepository appClientRepository;


    public boolean createNewAppUser(AppClient appClient) {

        logger.info("Create new user:{}", appClient);

        if (appClientRepository.findById(appClient.getId()).isPresent()) {
            logger.info("This user exists");
            return false;
        }
        if (appClientRepository.findAppUserByEmail(appClient.getEmail()) != null) {
            logger.info("This user with this email exists");
            return false;
        }
        if (appClientRepository.findAppUserByPhoneNumber(appClient.getPhoneNumber()) != null) {
            logger.info("This user with this phone number exists");
            return false;
        }
        appClient.setStatus(-1);
        appClientRepository.save(appClient);
        return true;
    }

    public AppClient updateAppUserStatus(String searchString, String type) {

        AppClient appClient = appClientRepository.findAppUserById(searchString);
        if (appClient == null) appClient = appClientRepository.findAppUserByEmail(searchString);
        if (appClient == null) appClient = appClientRepository.findAppUserByPhoneNumber(searchString);

        switch (type) {
            case "PLATINUM": {
                setStatusAppUser(appClient, Types.PLATINUM.ordinal());
                break;
            }
            case "GOLD": {
                setStatusAppUser(appClient, Types.GOLD.ordinal());
                break;
            }
            case "CLASSIC": {
                setStatusAppUser(appClient, Types.CLASSIC.ordinal());
                break;
            }
            default: {
                setStatusAppUser(appClient, -1);
                break;
            }
        }

        return appClientRepository.save(appClient);
    }

    private void setStatusAppUser(AppClient appClient, int statusAppUser) {
        appClient.setStatus(statusAppUser);
    }

}


