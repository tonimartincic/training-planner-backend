package hr.fer.trainingplanner.controller.fortime;

import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;
import hr.fer.trainingplanner.domain.fortime.ForTime;
import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.service.fortime.ForTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ForTimeController {

    private final ForTimeService service;

    @Autowired
    public ForTimeController(final ForTimeService service) {
        this.service = service;
    }

    @GetMapping("/api/for-time")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/for-time/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        ForTimeResponse response;
        try{
            ForTime entity = this.service.getById(id);
            response = this.service.getResponse(entity);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/for-time")
    public ResponseEntity<?> add(@RequestBody final ForTimeRequest request) {
        ForTimeResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/for-time")
    public ResponseEntity<?> edit(@RequestBody final ForTimeRequest request) {
        ForTimeResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/for-time/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
