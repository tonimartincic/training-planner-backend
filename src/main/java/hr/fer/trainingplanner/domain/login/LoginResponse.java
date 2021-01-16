package hr.fer.trainingplanner.domain.login;

import lombok.Data;

@Data
public class LoginResponse {

    private long userId;

    private String firstName;

    private String lastName;

    private String email;
}
