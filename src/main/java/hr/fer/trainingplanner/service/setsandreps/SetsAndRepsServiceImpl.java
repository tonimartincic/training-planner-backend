package hr.fer.trainingplanner.service.setsandreps;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndReps;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsRequest;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsResponse;
import hr.fer.trainingplanner.repository.setsandreps.SetsAndRepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SetsAndRepsServiceImpl implements SetsAndRepsService {

    private final SetsAndRepsRepository setsAndRepsRepository;

    @Autowired
    public SetsAndRepsServiceImpl(final SetsAndRepsRepository setsAndRepsRepository) {
        this.setsAndRepsRepository = setsAndRepsRepository;
    }

    @Override
    public List<SetsAndRepsResponse> getAll() {
        return getResponses(Lists.newArrayList(this.setsAndRepsRepository.findAll()));
    }

    @Override
    public SetsAndRepsResponse getById(Long id) {
        return getResponse(this.setsAndRepsRepository.findById(id));
    }

    @Override
    public SetsAndRepsResponse add(SetsAndRepsRequest request) {
        final SetsAndReps entity = new SetsAndReps(request);
        return getResponse(Optional.of(this.setsAndRepsRepository.save(entity)));
    }

    @Override
    public SetsAndRepsResponse edit(SetsAndRepsRequest request) {
        final Optional<SetsAndReps> entityFromDatabase = this.setsAndRepsRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final SetsAndReps entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.setsAndRepsRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.setsAndRepsRepository.deleteById(id);
    }

    private List<SetsAndRepsResponse> getResponses(final List<SetsAndReps> entities) {
        final List<SetsAndRepsResponse> responses = new ArrayList<>();

        for (final SetsAndReps entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private SetsAndRepsResponse getResponse(final Optional<SetsAndReps> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new SetsAndRepsResponse(entity.get());
    }
}
