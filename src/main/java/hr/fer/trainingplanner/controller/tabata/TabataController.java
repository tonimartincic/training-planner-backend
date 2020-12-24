package hr.fer.trainingplanner.controller.tabata;

import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.service.tabata.TabataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TabataController {

    private final TabataService service;

    @Autowired
    public TabataController(final TabataService service) {
        this.service = service;
    }

    @GetMapping("/api/tabata")
    public List<TabataResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/tabata/{id}")
    public TabataResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/tabata")
    public TabataResponse add(@RequestBody final TabataRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/tabata")
    public TabataResponse edit(@RequestBody final TabataRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/tabata/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
