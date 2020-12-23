package hr.fer.trainingplanner.service.EMOM;

import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;

import java.util.List;

public interface EMOMService {
    List<EMOMResponse> getAll();

    EMOMResponse getById(Long id);

    EMOMResponse add(EMOMRequest request);

    EMOMResponse edit(EMOMRequest request);

    void deleteById(Long id);
}
