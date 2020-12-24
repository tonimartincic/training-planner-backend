package hr.fer.trainingplanner.service.tabata.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.tabata.result.TabataResult;
import hr.fer.trainingplanner.domain.tabata.result.TabataResultRequest;
import hr.fer.trainingplanner.domain.tabata.result.TabataResultResponse;
import hr.fer.trainingplanner.repository.tabata.result.TabataResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TabataResultServiceImpl implements TabataResultService {

    private final TabataResultRepository tabataResultRepository;

    @Autowired
    public TabataResultServiceImpl(final TabataResultRepository tabataResultRepository) {
        this.tabataResultRepository = tabataResultRepository;
    }

    @Override
    public List<TabataResultResponse> getAll() {
        return getResponses(Lists.newArrayList(this.tabataResultRepository.findAll()));
    }

    @Override
    public TabataResultResponse getById(Long id) {
        return getResponse(this.tabataResultRepository.findById(id));
    }

    @Override
    public TabataResultResponse add(TabataResultRequest request) {
        final TabataResult entity = new TabataResult(request);
        return getResponse(Optional.of(this.tabataResultRepository.save(entity)));
    }

    @Override
    public TabataResultResponse edit(TabataResultRequest request) {
        final Optional<TabataResult> entityFromDatabase = this.tabataResultRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final TabataResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.tabataResultRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.tabataResultRepository.deleteById(id);
    }

    private List<TabataResultResponse> getResponses(final List<TabataResult> entities) {
        final List<TabataResultResponse> responses = new ArrayList<>();

        for (final TabataResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private TabataResultResponse getResponse(final Optional<TabataResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new TabataResultResponse(entity.get());
    }
}
