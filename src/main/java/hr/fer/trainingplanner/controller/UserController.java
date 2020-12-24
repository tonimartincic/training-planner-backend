package hr.fer.trainingplanner.controller;

import hr.fer.trainingplanner.domain.user.UserRequest;
import hr.fer.trainingplanner.domain.user.UserResponse;
import hr.fer.trainingplanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    @GetMapping("/api/users")
    public List<UserResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/users/{id}")
    public UserResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/users")
    public UserResponse add(@RequestBody final UserRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/users")
    public UserResponse edit(@RequestBody final UserRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/users/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
