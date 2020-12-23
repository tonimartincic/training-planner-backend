package hr.fer.trainingplanner.service.user;

import hr.fer.trainingplanner.domain.user.UserRequest;
import hr.fer.trainingplanner.domain.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse add(UserRequest request);

    UserResponse edit(UserRequest request);

    void deleteById(Long id);
}
