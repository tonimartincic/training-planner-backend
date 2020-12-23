package hr.fer.trainingplanner.service.tabata.result;

import hr.fer.trainingplanner.domain.tabata.result.TabataResultRequest;
import hr.fer.trainingplanner.domain.tabata.result.TabataResultResponse;

import java.util.List;

public interface TabataResultService {
    List<TabataResultResponse> getAll();

    TabataResultResponse getById(Long id);

    TabataResultResponse add(TabataResultRequest request);

    TabataResultResponse edit(TabataResultRequest request);

    void deleteById(Long id);
}
