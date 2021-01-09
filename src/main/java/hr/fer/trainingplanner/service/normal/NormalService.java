package hr.fer.trainingplanner.service.normal;

import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;

import java.util.List;

public interface NormalService {
    List<NormalResponse> getAll();

    NormalResponse getById(Long id);

    NormalResponse add(NormalRequest request);

    NormalResponse edit(NormalRequest request);

    void deleteById(Long id);
}
