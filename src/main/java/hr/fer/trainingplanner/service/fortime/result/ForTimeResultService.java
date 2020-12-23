package hr.fer.trainingplanner.service.fortime.result;

import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultRequest;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultResponse;

import java.util.List;

public interface ForTimeResultService {
    List<ForTimeResultResponse> getAll();

    ForTimeResultResponse getById(Long id);

    ForTimeResultResponse add(ForTimeResultRequest request);

    ForTimeResultResponse edit(ForTimeResultRequest request);

    void deleteById(Long id);
}
