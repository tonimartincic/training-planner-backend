package hr.fer.trainingplanner.controller.login;

import hr.fer.trainingplanner.domain.login.LoginRequest;
import hr.fer.trainingplanner.domain.login.LoginResponse;
import hr.fer.trainingplanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService service;

    @Autowired
    public LoginController(final UserService service) {
        this.service = service;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> add(@RequestBody final LoginRequest request) {
        LoginResponse response;
        try{
            response = this.service.login(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
