package hr.fer.trainingplanner.service.tabata.result;

import hr.fer.trainingplanner.domain.tabata.result.TabataResultRequest;
import hr.fer.trainingplanner.domain.tabata.result.TabataResultResponse;
import hr.fer.trainingplanner.repository.tabata.result.TabataResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabataResultServiceImpl implements TabataResultService {

    private final TabataResultRepository tabataResultRepository;

    @Autowired
    public TabataResultServiceImpl(final TabataResultRepository tabataResultRepository) {
        this.tabataResultRepository = tabataResultRepository;
    }

    @Override
    public List<TabataResultResponse> getAll() {
        return null;
    }

    @Override
    public TabataResultResponse getById(Long id) {
        return null;
    }

    @Override
    public TabataResultResponse add(TabataResultRequest request) {
        return null;
    }

    @Override
    public TabataResultResponse edit(TabataResultRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<AMRAPResultResponse> getResponses(final List<AMRAPResult> entities) {
        final List<AMRAPResultResponse> responses = new ArrayList<>();

        for (final AMRAPResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private AMRAPResultResponse getResponse(final Optional<AMRAPResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new AMRAPResultResponse(entity.get());
    }
}
