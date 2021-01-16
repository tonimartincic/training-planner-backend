package hr.fer.trainingplanner.controller.normal;

import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;
import hr.fer.trainingplanner.service.normal.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class NormalController {

    private final NormalService service;

    @Autowired
    public NormalController(final NormalService service) {
        this.service = service;
    }

    @GetMapping("/api/normal")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/normal/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        NormalResponse response;
        try{
            Normal entity = this.service.getById(id);
            response = this.service.getResponse(entity);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/normal")
    public ResponseEntity<?> add(@RequestBody final NormalRequest request) {
        NormalResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/normal")
    public ResponseEntity<?> edit(@RequestBody final NormalRequest request) {
        NormalResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/normal/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
