package hr.fer.trainingplanner.service.normal;

import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;

import java.util.List;

public interface NormalService {

    List<NormalResponse> getAll();

    Normal getById(Long id);

    NormalResponse add(NormalRequest request);

    NormalResponse edit(NormalRequest request);

    void deleteById(Long id);

    List<NormalResponse> getResponses(final List<Normal> entities);

    NormalResponse getResponse(final Normal entity);

    List<Normal> getEntities(final List<NormalRequest> requests);

    Normal getEntity(final NormalRequest request);
}
