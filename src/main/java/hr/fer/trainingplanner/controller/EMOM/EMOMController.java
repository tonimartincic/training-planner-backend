package hr.fer.trainingplanner.controller.EMOM;

import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;
import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.service.EMOM.EMOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EMOMController {

    private final EMOMService service;

    @Autowired
    public EMOMController(final EMOMService service) {
        this.service = service;
    }

    @GetMapping("/api/emom")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/emom/{id}")
    public ResponseEntity<?> getById(@PathVariable final Long id) {
        EMOMResponse response;
        try{
            EMOM entity = this.service.getById(id);
            response = this.service.getResponse(entity);
        } catch (IllegalArgumentException | NoSuchElementException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/emom")
    public ResponseEntity<?> add(@RequestBody final EMOMRequest request) {
        EMOMResponse response;
        try{
            response = this.service.add(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/api/emom")
    public ResponseEntity<?> edit(@RequestBody final EMOMRequest request) {
        EMOMResponse response;
        try{
            response = this.service.edit(request);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/api/emom/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        try {
            this.service.deleteById(id);
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
