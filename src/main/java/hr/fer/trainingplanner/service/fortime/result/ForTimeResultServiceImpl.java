package hr.fer.trainingplanner.service.fortime.result;

import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultRequest;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultResponse;
import hr.fer.trainingplanner.repository.fortime.result.ForTimeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForTimeResultServiceImpl implements ForTimeResultService {

    private final ForTimeResultRepository forTimeResultRepository;

    @Autowired
    public ForTimeResultServiceImpl(final ForTimeResultRepository forTimeResultRepository) {
        this.forTimeResultRepository = forTimeResultRepository;
    }

    @Override
    public List<ForTimeResultResponse> getAll() {
        return null;
    }

    @Override
    public ForTimeResultResponse getById(Long id) {
        return null;
    }

    @Override
    public ForTimeResultResponse add(ForTimeResultRequest request) {
        return null;
    }

    @Override
    public ForTimeResultResponse edit(ForTimeResultRequest request) {
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
