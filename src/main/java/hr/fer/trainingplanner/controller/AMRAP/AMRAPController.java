package hr.fer.trainingplanner.controller.AMRAP;

import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;
import hr.fer.trainingplanner.service.AMRAP.AMRAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AMRAPController {

    private final AMRAPService service;

    @Autowired
    public AMRAPController(final AMRAPService service) {
        this.service = service;
    }

    @GetMapping("/api/amrap")
    public List<AMRAPResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/amrap/{id}")
    public AMRAPResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/amrap")
    public AMRAPResponse add(@RequestBody final AMRAPRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/amrap")
    public AMRAPResponse edit(@RequestBody final AMRAPRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/amrap/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
