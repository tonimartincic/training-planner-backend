package hr.fer.trainingplanner.service.setsandreps.result;

import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultRequest;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultResponse;

import java.util.List;

public interface SetsAndRepsResultService {
    List<SetsAndRepsResultResponse> getAll();

    SetsAndRepsResultResponse getById(Long id);

    SetsAndRepsResultResponse add(SetsAndRepsResultRequest request);

    SetsAndRepsResultResponse edit(SetsAndRepsResultRequest request);

    void deleteById(Long id);
}
