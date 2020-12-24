package hr.fer.trainingplanner.controller.setsandreps.result;

import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultRequest;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultResponse;
import hr.fer.trainingplanner.service.setsandreps.result.SetsAndRepsResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetsAndRepsResultController {

    private final SetsAndRepsResultService service;

    @Autowired
    public SetsAndRepsResultController(final SetsAndRepsResultService service) {
        this.service = service;
    }

    @GetMapping("/api/sets-and-reps-results")
    public List<SetsAndRepsResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/sets-and-reps-results/{id}")
    public SetsAndRepsResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/sets-and-reps-results")
    public SetsAndRepsResultResponse add(@RequestBody final SetsAndRepsResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/sets-and-reps-results")
    public SetsAndRepsResultResponse edit(@RequestBody final SetsAndRepsResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/sets-and-reps-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
