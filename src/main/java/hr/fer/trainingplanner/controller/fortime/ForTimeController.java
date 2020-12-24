package hr.fer.trainingplanner.controller.fortime;

import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.service.fortime.ForTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ForTimeController {

    private final ForTimeService service;

    @Autowired
    public ForTimeController(final ForTimeService service) {
        this.service = service;
    }

    @GetMapping("/api/for-time")
    public List<ForTimeResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/for-time/{id}")
    public ForTimeResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/for-time")
    public ForTimeResponse add(@RequestBody final ForTimeRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/for-time")
    public ForTimeResponse edit(@RequestBody final ForTimeRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/for-time/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
