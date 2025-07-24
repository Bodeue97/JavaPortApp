package pl.bohdanlukaszewicz.JavaPortApp.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bohdanlukaszewicz.JavaPortApp.User.Model.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByEmail(String email);

}
