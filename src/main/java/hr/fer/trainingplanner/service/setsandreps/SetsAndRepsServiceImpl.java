package hr.fer.trainingplanner.service.setsandreps;

import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsRequest;
import hr.fer.trainingplanner.domain.setsandreps.SetsAndRepsResponse;
import hr.fer.trainingplanner.repository.setsandreps.SetsAndRepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetsAndRepsServiceImpl implements SetsAndRepsService {

    private final SetsAndRepsRepository setsAndRepsRepository;

    @Autowired
    public SetsAndRepsServiceImpl(final SetsAndRepsRepository setsAndRepsRepository) {
        this.setsAndRepsRepository = setsAndRepsRepository;
    }

    @Override
    public List<SetsAndRepsResponse> getAll() {
        return null;
    }

    @Override
    public SetsAndRepsResponse getById(Long id) {
        return null;
    }

    @Override
    public SetsAndRepsResponse add(SetsAndRepsRequest request) {
        return null;
    }

    @Override
    public SetsAndRepsResponse edit(SetsAndRepsRequest request) {
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
