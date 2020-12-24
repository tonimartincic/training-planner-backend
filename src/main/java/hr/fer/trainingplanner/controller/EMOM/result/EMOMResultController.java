package hr.fer.trainingplanner.controller.EMOM.result;

import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultRequest;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultResponse;
import hr.fer.trainingplanner.service.EMOM.result.EMOMResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EMOMResultController {

    private final EMOMResultService service;

    @Autowired
    public EMOMResultController(final EMOMResultService service) {
        this.service = service;
    }

    @GetMapping("/api/emom-results")
    public List<EMOMResultResponse> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/api/emom-results/{id}")
    public EMOMResultResponse getById(@PathVariable final Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/api/emom-results")
    public EMOMResultResponse add(@RequestBody final EMOMResultRequest request) {
        return this.service.add(request);
    }

    @PutMapping("/api/emom-results")
    public EMOMResultResponse edit(@RequestBody final EMOMResultRequest request) {
        return this.service.edit(request);
    }

    @DeleteMapping("/api/emom-results/{id}")
    public void delete(@PathVariable final Long id) {
        this.service.deleteById(id);
    }
}
