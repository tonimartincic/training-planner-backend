package hr.fer.trainingplanner.service.setsandreps;

import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsRequest;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsResponse;

import java.util.List;

public interface SetsAndRepsService {
    List<SetsAndRepsResponse> getAll();

    SetsAndRepsResponse getById(Long id);

    SetsAndRepsResponse add(SetsAndRepsRequest request);

    SetsAndRepsResponse edit(SetsAndRepsRequest request);

    void deleteById(Long id);
}
