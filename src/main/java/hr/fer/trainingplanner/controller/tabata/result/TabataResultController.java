package hr.fer.trainingplanner.controller.tabata.result;

import hr.fer.trainingplanner.domain.tabata.result.TabataResultRequest;
import hr.fer.trainingplanner.domain.tabata.result.TabataResultResponse;
import hr.fer.trainingplanner.service.tabata.result.TabataResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TabataResultController {

    private final TabataResultService service;

    @Autowired
    public TabataResultController(final TabataResultService service) {
        this.service = service;
    }

    @GetMapping("/api/tabata-results")
    public List<TabataResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/tabata-results/{id}")
    public TabataResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/tabata-results")
    public TabataResultResponse add(@RequestBody final TabataResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/tabata-results")
    public TabataResultResponse edit(@RequestBody final TabataResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/tabata-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
