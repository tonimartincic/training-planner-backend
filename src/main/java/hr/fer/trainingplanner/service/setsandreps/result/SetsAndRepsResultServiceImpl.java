package hr.fer.trainingplanner.service.setsandreps.result;

import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultRequest;
import hr.fer.trainingplanner.domain.setsandreps.result.SetsAndRepsResultResponse;
import hr.fer.trainingplanner.repository.setsandreps.result.SetsAndRepsResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetsAndRepsResultServiceImpl implements SetsAndRepsResultService {

    private final SetsAndRepsResultRepository setsAndRepsResultRepository;

    @Autowired
    public SetsAndRepsResultServiceImpl(final SetsAndRepsResultRepository setsAndRepsResultRepository) {
        this.setsAndRepsResultRepository = setsAndRepsResultRepository;
    }

    @Override
    public List<SetsAndRepsResultResponse> getAll() {
        return null;
    }

    @Override
    public SetsAndRepsResultResponse getById(Long id) {
        return null;
    }

    @Override
    public SetsAndRepsResultResponse add(SetsAndRepsResultRequest request) {
        return null;
    }

    @Override
    public SetsAndRepsResultResponse edit(SetsAndRepsResultRequest request) {
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
