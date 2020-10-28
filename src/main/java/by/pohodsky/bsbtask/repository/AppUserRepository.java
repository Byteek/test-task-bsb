package by.pohodsky.bsbtask.repository;

import by.pohodsky.bsbtask.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    AppUser findAppUserById(String id);

    AppUser findAppUserByEmail(String email);

    AppUser findAppUserByPhoneNumber(String phoneNumber);

}
