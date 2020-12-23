package hr.fer.trainingplanner.service.fortime;

import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;

import java.util.List;

public interface ForTimeService {
    List<ForTimeResponse> getAll();

    ForTimeResponse getById(Long id);

    ForTimeResponse add(ForTimeRequest request);

    ForTimeResponse edit(ForTimeRequest request);

    void deleteById(Long id);
}
