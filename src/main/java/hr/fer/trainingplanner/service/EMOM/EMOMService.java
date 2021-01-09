package hr.fer.trainingplanner.service.EMOM;

import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;

import java.util.List;

public interface EMOMService {
    List<EMOMResponse> getAll();

    EMOM getById(Long id);

    EMOMResponse add(EMOMRequest request);

    EMOMResponse edit(EMOMRequest request);

    void deleteById(Long id);

    List<EMOMResponse> getResponses(final List<EMOM> entities);

    EMOMResponse getResponse(final EMOM entity);

    List<EMOM> getEntities(final List<EMOMRequest> requests);

    EMOM getEntity(final EMOMRequest request);
}
