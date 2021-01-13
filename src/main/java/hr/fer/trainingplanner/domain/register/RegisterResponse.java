package hr.fer.trainingplanner.domain.register;

import lombok.Data;

@Data
public class RegisterResponse {

    private long userId;

    private String firstName;

    private String lastName;

    private String email;
}
