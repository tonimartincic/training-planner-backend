package hr.fer.trainingplanner.service.fortime;

import hr.fer.trainingplanner.domain.fortime.ForTime;
import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;

import java.util.List;

public interface ForTimeService {
    List<ForTimeResponse> getAll();

    ForTime getById(Long id);

    ForTimeResponse add(ForTimeRequest request);

    ForTimeResponse edit(ForTimeRequest request);

    void deleteById(Long id);

    List<ForTimeResponse> getResponses(final List<ForTime> entities);

    ForTimeResponse getResponse(final ForTime entity);

    List<ForTime> getEntities(final List<ForTimeRequest> requests);

    ForTime getEntity(final ForTimeRequest request);
}
