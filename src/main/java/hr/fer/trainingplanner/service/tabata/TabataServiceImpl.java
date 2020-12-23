package hr.fer.trainingplanner.service.tabata;

import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.repository.tabata.TabataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabataServiceImpl implements TabataService {

    private final TabataRepository tabataRepository;

    @Autowired
    public TabataServiceImpl(final TabataRepository tabataRepository) {
        this.tabataRepository = tabataRepository;
    }

    @Override
    public List<TabataResponse> getAll() {
        return null;
    }

    @Override
    public TabataResponse getById(Long id) {
        return null;
    }

    @Override
    public TabataResponse add(TabataRequest request) {
        return null;
    }

    @Override
    public TabataResponse edit(TabataRequest request) {
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
