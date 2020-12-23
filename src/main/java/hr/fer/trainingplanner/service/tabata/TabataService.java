package hr.fer.trainingplanner.service.tabata;

import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;

import java.util.List;

public interface TabataService {
    List<TabataResponse> getAll();

    TabataResponse getById(Long id);

    TabataResponse add(TabataRequest request);

    TabataResponse edit(TabataRequest request);

    void deleteById(Long id);
}
