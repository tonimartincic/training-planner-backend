package hr.fer.trainingplanner.controller.AMRAP;

import hr.fer.trainingplanner.domain.AMRAP.AMRAP;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;
import hr.fer.trainingplanner.service.AMRAP.AMRAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class AMRAPController {

    private final AMRAPService service;

    @Autowired
    public AMRAPController(final AMRAPService service) {
        this.service = service;
    }

    @GetMapping("/api/amrap")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/amrap/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        AMRAPResponse response;
        try{
            AMRAP entity = this.service.getById(id);
            response = this.service.getResponse(entity);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/amrap")
    public ResponseEntity<?> add(@RequestBody final AMRAPRequest request) {
        AMRAPResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/amrap")
    public ResponseEntity<?> edit(@RequestBody final AMRAPRequest request) {
        AMRAPResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/amrap/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
