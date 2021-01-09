package hr.fer.trainingplanner.service.normal.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.normal.result.NormalResult;
import hr.fer.trainingplanner.domain.normal.result.NormalResultRequest;
import hr.fer.trainingplanner.domain.normal.result.NormalResultResponse;
import hr.fer.trainingplanner.repository.normal.result.NormalResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NormalResultServiceImpl implements NormalResultService {

    private final NormalResultRepository repository;

    @Autowired
    public NormalResultServiceImpl(final NormalResultRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NormalResultResponse> getAll() {
        return getResponses(Lists.newArrayList(this.repository.findAll()));
    }

    @Override
    public NormalResultResponse getById(Long id) {
        return getResponse(this.repository.findById(id));
    }

    @Override
    public NormalResultResponse add(NormalResultRequest request) {
        final NormalResult entity = new NormalResult(request);
        return getResponse(Optional.of(this.repository.save(entity)));
    }

    @Override
    public NormalResultResponse edit(NormalResultRequest request) {
        final Optional<NormalResult> entityFromDatabase = this.repository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final NormalResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.repository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    private List<NormalResultResponse> getResponses(final List<NormalResult> entities) {
        final List<NormalResultResponse> responses = new ArrayList<>();

        for (final NormalResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private NormalResultResponse getResponse(final Optional<NormalResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new NormalResultResponse(entity.get());
    }
}
