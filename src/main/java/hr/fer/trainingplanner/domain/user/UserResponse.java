package hr.fer.trainingplanner.domain.user;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    public UserResponse(User user) {
    }
}
