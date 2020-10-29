package by.pohodsky.bsbtask.repository;
import by.pohodsky.bsbtask.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByName(String name);
}