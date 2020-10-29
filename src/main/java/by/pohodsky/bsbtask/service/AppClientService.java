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


    public boolean createNewAppClient(AppClient appClient) {

        logger.info("Create new client:{}", appClient);

        if (appClientRepository.findById(appClient.getId()).isPresent()) {
            logger.info("This client exists");
            return false;
        }
        if (appClientRepository.findAppClientByEmail(appClient.getEmail()) != null) {
            logger.info("This client with this email exists");
            return false;
        }
        if (appClientRepository.findAppClientByPhoneNumber(appClient.getPhoneNumber()) != null) {
            logger.info("This client with this phone number exists");
            return false;
        }
        appClient.setStatus(-1);
        appClientRepository.save(appClient);
        return true;
    }

    public AppClient updateAppClientStatus(String searchString, String type) {

        AppClient appClient = appClientRepository.findAppClientById(searchString);
        if (appClient == null) appClient = appClientRepository.findAppClientByEmail(searchString);
        if (appClient == null) appClient = appClientRepository.findAppClientByPhoneNumber(searchString);

        switch (type) {
            case "PLATINUM": {
                setStatusAppClient(appClient, Types.PLATINUM.ordinal());
                break;
            }
            case "GOLD": {
                setStatusAppClient(appClient, Types.GOLD.ordinal());
                break;
            }
            case "CLASSIC": {
                setStatusAppClient(appClient, Types.CLASSIC.ordinal());
                break;
            }
            default: {
                setStatusAppClient(appClient, -1);
                break;
            }
        }

        return appClientRepository.save(appClient);
    }

    private void setStatusAppClient(AppClient appClient, int statusAppUser) {
        appClient.setStatus(statusAppUser);
    }

    public AppClient findAppClientByPhoneNumber(String linkedAppClientPhoneNumber) {
        return appClientRepository.findAppClientByPhoneNumber(linkedAppClientPhoneNumber);
    }
}


