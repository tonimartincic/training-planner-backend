package hr.fer.trainingplanner.controller.register;

import hr.fer.trainingplanner.domain.register.RegisterRequest;
import hr.fer.trainingplanner.domain.register.RegisterResponse;
import hr.fer.trainingplanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {

    private final UserService service;

    @Autowired
    public RegisterController(final UserService service) {
        this.service = service;
    }

    @PostMapping("/api/register")
    public RegisterResponse add(@RequestBody final RegisterRequest request) {
        return this.service.register(request);
    }
}
