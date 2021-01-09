package hr.fer.trainingplanner.controller.normal;

import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;
import hr.fer.trainingplanner.service.normal.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NormalController {

    private final NormalService service;

    @Autowired
    public NormalController(final NormalService service) {
        this.service = service;
    }

    @GetMapping("/api/normal")
    public List<NormalResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/normal/{id}")
    public NormalResponse getById(@PathVariable final Long id) {
        Normal entity = this.service.getById(id);
        NormalResponse response = this.service.getResponse(entity);

        return response;
    }

    @PostMapping("/api/normal")
    public NormalResponse add(@RequestBody final NormalRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/normal")
    public NormalResponse edit(@RequestBody final NormalRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/normal/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
