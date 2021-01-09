package hr.fer.trainingplanner.service.AMRAP;

import hr.fer.trainingplanner.domain.AMRAP.AMRAP;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;

import java.util.List;

public interface AMRAPService {
    List<AMRAPResponse> getAll();

    AMRAP getById(Long id);

    AMRAPResponse add(AMRAPRequest request);

    AMRAPResponse edit(AMRAPRequest request);

    void deleteById(Long id);

    List<AMRAPResponse> getResponses(final List<AMRAP> entities);

    AMRAPResponse getResponse(final AMRAP entity);

    List<AMRAP> getEntities(final List<AMRAPRequest> requests);

    AMRAP getEntity(final AMRAPRequest request);
}
