package hr.fer.trainingplanner.service.AMRAP.result;

import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultRequest;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultResponse;

import java.util.List;

public interface AMRAPResultService {
    List<AMRAPResultResponse> getAll();

    AMRAPResultResponse getById(Long id);

    AMRAPResultResponse add(AMRAPResultRequest request);

    AMRAPResultResponse edit(AMRAPResultRequest request);

    void deleteById(Long id);
}
