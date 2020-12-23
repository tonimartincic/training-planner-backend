package hr.fer.trainingplanner.service.AMRAP;

import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;

import java.util.List;

public interface AMRAPService {
    List<AMRAPResponse> getAll();

    AMRAPResponse getById(Long id);

    AMRAPResponse add(AMRAPRequest request);

    AMRAPResponse edit(AMRAPRequest request);

    void deleteById(Long id);
}
