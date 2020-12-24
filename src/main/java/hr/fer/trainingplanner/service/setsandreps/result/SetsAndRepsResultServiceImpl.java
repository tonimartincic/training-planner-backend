package hr.fer.trainingplanner.service.setsandreps.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResult;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultRequest;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultResponse;
import hr.fer.trainingplanner.repository.setsandreps.result.SetsAndRepsResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SetsAndRepsResultServiceImpl implements SetsAndRepsResultService {

    private final SetsAndRepsResultRepository setsAndRepsResultRepository;

    @Autowired
    public SetsAndRepsResultServiceImpl(final SetsAndRepsResultRepository setsAndRepsResultRepository) {
        this.setsAndRepsResultRepository = setsAndRepsResultRepository;
    }

    @Override
    public List<SetsAndRepsResultResponse> getAll() {
        return getResponses(Lists.newArrayList(this.setsAndRepsResultRepository.findAll()));
    }

    @Override
    public SetsAndRepsResultResponse getById(Long id) {
        return getResponse(this.setsAndRepsResultRepository.findById(id));
    }

    @Override
    public SetsAndRepsResultResponse add(SetsAndRepsResultRequest request) {
        final SetsAndRepsResult entity = new SetsAndRepsResult(request);
        return getResponse(Optional.of(this.setsAndRepsResultRepository.save(entity)));
    }

    @Override
    public SetsAndRepsResultResponse edit(SetsAndRepsResultRequest request) {
        final Optional<SetsAndRepsResult> entityFromDatabase = this.setsAndRepsResultRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final SetsAndRepsResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.setsAndRepsResultRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.setsAndRepsResultRepository.deleteById(id);
    }

    private List<SetsAndRepsResultResponse> getResponses(final List<SetsAndRepsResult> entities) {
        final List<SetsAndRepsResultResponse> responses = new ArrayList<>();

        for (final SetsAndRepsResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private SetsAndRepsResultResponse getResponse(final Optional<SetsAndRepsResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new SetsAndRepsResultResponse(entity.get());
    }
}
