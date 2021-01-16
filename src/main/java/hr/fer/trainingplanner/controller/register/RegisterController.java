package hr.fer.trainingplanner.controller.register;

import hr.fer.trainingplanner.domain.register.RegisterRequest;
import hr.fer.trainingplanner.domain.register.RegisterResponse;
import hr.fer.trainingplanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserService service;

    @Autowired
    public RegisterController(final UserService service) {
        this.service = service;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> add(@RequestBody final RegisterRequest request) {
        RegisterResponse response;
        try{
            response = this.service.register(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
