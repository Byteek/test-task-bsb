package by.pohodsky.bsbtask.service;

import by.pohodsky.bsbtask.entity.AppUser;
import by.pohodsky.bsbtask.repository.AppUserRepository;
import by.pohodsky.bsbtask.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public AppUser findByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    public void saveUser(AppUser appUser) {
        appUser.setRole(
                userRoleRepository.findById(0).orElseThrow()
        );
        appUserRepository.save(appUser);
    }
}