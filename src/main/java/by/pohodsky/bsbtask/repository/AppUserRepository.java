package by.pohodsky.bsbtask.repository;

import by.pohodsky.bsbtask.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByLogin(String login);
}
