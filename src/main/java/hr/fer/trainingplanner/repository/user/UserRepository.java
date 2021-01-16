package hr.fer.trainingplanner.repository.user;

import hr.fer.trainingplanner.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
