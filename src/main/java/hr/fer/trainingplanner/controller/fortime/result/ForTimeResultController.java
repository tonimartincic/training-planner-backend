package hr.fer.trainingplanner.controller.fortime.result;

import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultRequest;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultResponse;
import hr.fer.trainingplanner.service.fortime.result.ForTimeResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForTimeResultController {

    private final ForTimeResultService service;

    @Autowired
    public ForTimeResultController(final ForTimeResultService service) {
        this.service = service;
    }

    @GetMapping("/api/for-time-results")
    public List<ForTimeResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/for-time-results/{id}")
    public ForTimeResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/for-time-results")
    public ForTimeResultResponse add(@RequestBody final ForTimeResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/for-time-results")
    public ForTimeResultResponse edit(@RequestBody final ForTimeResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/for-time-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
