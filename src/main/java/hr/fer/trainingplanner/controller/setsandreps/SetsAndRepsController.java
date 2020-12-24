package hr.fer.trainingplanner.controller.setsandreps;

import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsRequest;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsResponse;
import hr.fer.trainingplanner.service.setsandreps.SetsAndRepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SetsAndRepsController {

    private final SetsAndRepsService service;

    @Autowired
    public SetsAndRepsController(final SetsAndRepsService service) {
        this.service = service;
    }

    @GetMapping("/api/sets-and-reps")
    public List<SetsAndRepsResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/sets-and-reps/{id}")
    public SetsAndRepsResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/sets-and-reps")
    public SetsAndRepsResponse add(@RequestBody final SetsAndRepsRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/sets-and-reps")
    public SetsAndRepsResponse edit(@RequestBody final SetsAndRepsRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/sets-and-reps/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
