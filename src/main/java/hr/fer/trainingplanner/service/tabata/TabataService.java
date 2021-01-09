package hr.fer.trainingplanner.service.tabata;

import hr.fer.trainingplanner.domain.tabata.Tabata;
import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;

import java.util.List;

public interface TabataService {

    List<TabataResponse> getAll();

    Tabata getById(Long id);

    TabataResponse add(TabataRequest request);

    TabataResponse edit(TabataRequest request);

    void deleteById(Long id);

    List<TabataResponse> getResponses(final List<Tabata> entities);

    TabataResponse getResponse(final Tabata entity);

    List<Tabata> getEntities(final List<TabataRequest> requests);

    Tabata getEntity(final TabataRequest request);
}
