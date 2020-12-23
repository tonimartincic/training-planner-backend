package hr.fer.trainingplanner.service.user;

import hr.fer.trainingplanner.domain.user.UserRequest;
import hr.fer.trainingplanner.domain.user.UserResponse;
import hr.fer.trainingplanner.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> getAll() {
        return null;
    }

    @Override
    public UserResponse getById(Long id) {
        return null;
    }

    @Override
    public UserResponse add(UserRequest request) {
        return null;
    }

    @Override
    public UserResponse edit(UserRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<AMRAPResultResponse> getResponses(final List<AMRAPResult> entities) {
        final List<AMRAPResultResponse> responses = new ArrayList<>();

        for (final AMRAPResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private AMRAPResultResponse getResponse(final Optional<AMRAPResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new AMRAPResultResponse(entity.get());
    }
}
