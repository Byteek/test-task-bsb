package by.pohodsky.bsbtask.repository;

import by.pohodsky.bsbtask.entity.AppClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppClientRepository extends JpaRepository<AppClient, String> {

    AppClient findAppUserById(String id);

    AppClient findAppUserByEmail(String email);

    AppClient findAppUserByPhoneNumber(String phoneNumber);

}
