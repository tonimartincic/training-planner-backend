package hr.fer.trainingplanner.controller.tabata;

import hr.fer.trainingplanner.domain.normal.NormalResponse;
import hr.fer.trainingplanner.domain.tabata.Tabata;
import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.service.tabata.TabataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TabataController {

    private final TabataService service;

    @Autowired
    public TabataController(final TabataService service) {
        this.service = service;
    }

    @GetMapping("/api/tabata")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/tabata/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        TabataResponse response;
        try{
            Tabata entity = this.service.getById(id);
            response = this.service.getResponse(entity);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/tabata")
    public ResponseEntity<?> add(@RequestBody final TabataRequest request) {
        TabataResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/tabata")
    public ResponseEntity<?> edit(@RequestBody final TabataRequest request) {
        TabataResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/tabata/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
