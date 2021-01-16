package hr.fer.trainingplanner.controller.exercise;

import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.domain.exercise.ExerciseRequest;
import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;
import hr.fer.trainingplanner.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ExerciseController {

    private final ExerciseService service;

    @Autowired
    public ExerciseController(final ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/api/exercise")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/exercise/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        ExerciseResponse response;
        try{
            response = this.service.getById(id);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/api/exercise")
    public ResponseEntity<?> add(@RequestBody final ExerciseRequest request) {
        ExerciseResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/exercise")
    public ResponseEntity<?> edit(@RequestBody final ExerciseRequest request) {
        ExerciseResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/exercise/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
