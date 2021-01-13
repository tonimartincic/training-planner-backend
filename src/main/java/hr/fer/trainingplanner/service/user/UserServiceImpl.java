package hr.fer.trainingplanner.service.user;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.register.RegisterRequest;
import hr.fer.trainingplanner.domain.register.RegisterResponse;
import hr.fer.trainingplanner.domain.user.User;
import hr.fer.trainingplanner.domain.user.UserRequest;
import hr.fer.trainingplanner.domain.user.UserResponse;
import hr.fer.trainingplanner.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAll() {
        return getResponses(Lists.newArrayList(this.userRepository.findAll()));
    }

    @Override
    public UserResponse getById(Long id) {
        return getResponse(this.userRepository.findById(id));
    }

    @Override
    public UserResponse add(UserRequest request) {
        final User entity = new User(request);
        return getResponse(Optional.of(this.userRepository.save(entity)));
    }

    @Override
    public UserResponse edit(UserRequest request) {
        final Optional<User> entityFromDatabase = this.userRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final User entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.userRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if (request.getPassword() == null
                || request.getPassword().isEmpty()
                || request.getConfirmPassword() == null
                || request.getConfirmPassword().isEmpty()
                || !request.getPassword().equals(request.getConfirmPassword())) {
            throw new IllegalArgumentException("Invalid password data");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        user = this.userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setUserId(user.getId());
        registerResponse.setFirstName(user.getFirstName());
        registerResponse.setLastName(user.getLastName());
        registerResponse.setEmail(user.getEmail());

        return registerResponse;
    }

    private List<UserResponse> getResponses(final List<User> entities) {
        final List<UserResponse> responses = new ArrayList<>();

        for (final User entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private UserResponse getResponse(final Optional<User> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new UserResponse(entity.get());
    }
}
