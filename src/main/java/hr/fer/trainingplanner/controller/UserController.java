package hr.fer.trainingplanner.controller;

import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.domain.user.UserRequest;
import hr.fer.trainingplanner.domain.user.UserResponse;
import hr.fer.trainingplanner.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(final UserService service) {
        this.service = service;
    }

    @GetMapping("/api/users")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        UserResponse response;
        try{
            response = this.service.getById(id);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/users")
    public ResponseEntity<?> add(@RequestBody final UserRequest request) {
        UserResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/users")
    public ResponseEntity<?> edit(@RequestBody final UserRequest request) {
        UserResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
