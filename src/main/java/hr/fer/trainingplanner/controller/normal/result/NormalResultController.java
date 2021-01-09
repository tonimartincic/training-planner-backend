package hr.fer.trainingplanner.controller.normal.result;

import hr.fer.trainingplanner.domain.normal.result.NormalResultRequest;
import hr.fer.trainingplanner.domain.normal.result.NormalResultResponse;
import hr.fer.trainingplanner.service.normal.result.NormalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NormalResultController {

    private final NormalResultService service;

    @Autowired
    public NormalResultController(final NormalResultService service) {
        this.service = service;
    }

    @GetMapping("/api/normal-results")
    public List<NormalResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/normal-results/{id}")
    public NormalResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/normal-results")
    public NormalResultResponse add(@RequestBody final NormalResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/normal-results")
    public NormalResultResponse edit(@RequestBody final NormalResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/normal-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
