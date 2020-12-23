package hr.fer.trainingplanner.service.EMOM.result;

import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultRequest;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultResponse;

import java.util.List;

public interface EMOMResultService {
    List<EMOMResultResponse> getAll();

    EMOMResultResponse getById(Long id);

    EMOMResultResponse add(EMOMResultRequest request);

    EMOMResultResponse edit(EMOMResultRequest request);

    void deleteById(Long id);
}
