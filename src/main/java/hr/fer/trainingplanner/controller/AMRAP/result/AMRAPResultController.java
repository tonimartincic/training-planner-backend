package hr.fer.trainingplanner.controller.AMRAP.result;

import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultRequest;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultResponse;
import hr.fer.trainingplanner.service.AMRAP.result.AMRAPResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AMRAPResultController {

    private final AMRAPResultService service;

    @Autowired
    public AMRAPResultController(final AMRAPResultService service) {
        this.service = service;
    }

    @GetMapping("/api/amrap-results")
    public List<AMRAPResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/amrap-results/{id}")
    public AMRAPResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/amrap-results")
    public AMRAPResultResponse add(@RequestBody final AMRAPResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/amrap-results")
    public AMRAPResultResponse edit(@RequestBody final AMRAPResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/amrap-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
