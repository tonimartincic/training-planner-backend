package hr.fer.trainingplanner.controller.EMOM;

import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.service.EMOM.EMOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EMOMController {

    private final EMOMService service;

    @Autowired
    public EMOMController(final EMOMService service) {
        this.service = service;
    }

    @GetMapping("/api/emom")
    public List<EMOMResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/emom/{id}")
    public EMOMResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/emom")
    public EMOMResponse add(@RequestBody final EMOMRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/emom")
    public EMOMResponse edit(@RequestBody final EMOMRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/emom/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
