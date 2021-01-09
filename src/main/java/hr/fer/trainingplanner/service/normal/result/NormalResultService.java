package hr.fer.trainingplanner.service.normal.result;

import hr.fer.trainingplanner.domain.normal.result.NormalResultRequest;
import hr.fer.trainingplanner.domain.normal.result.NormalResultResponse;

import java.util.List;

public interface NormalResultService {
    List<NormalResultResponse> getAll();

    NormalResultResponse getById(Long id);

    NormalResultResponse add(NormalResultRequest request);

    NormalResultResponse edit(NormalResultRequest request);

    void deleteById(Long id);
}
