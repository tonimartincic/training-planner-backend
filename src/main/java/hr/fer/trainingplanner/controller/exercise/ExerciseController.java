package hr.fer.trainingplanner.controller.exercise;

import hr.fer.trainingplanner.domain.exercise.ExerciseRequest;
import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;
import hr.fer.trainingplanner.service.exercise.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExerciseController {

    private final ExerciseService service;

    @Autowired
    public ExerciseController(final ExerciseService service) {
        this.service = service;
    }

    @GetMapping("/api/exercise")
    public List<ExerciseResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/exercise/{id}")
    public ExerciseResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/exercise")
    public ExerciseResponse add(@RequestBody final ExerciseRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/exercise")
    public ExerciseResponse edit(@RequestBody final ExerciseRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/exercise/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
