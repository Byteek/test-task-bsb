package by.pohodsky.bsbtask.repository;

import by.pohodsky.bsbtask.entity.AppClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppClientRepository extends JpaRepository<AppClient, String> {

    AppClient findAppClientById(String id);

    AppClient findAppClientByEmail(String email);

    AppClient findAppClientByPhoneNumber(String phoneNumber);

}
