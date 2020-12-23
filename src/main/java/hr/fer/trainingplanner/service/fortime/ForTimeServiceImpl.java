package hr.fer.trainingplanner.service.fortime;

import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.repository.fortime.ForTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForTimeServiceImpl implements ForTimeService {

    private final ForTimeRepository forTimeRepository;

    @Autowired
    public ForTimeServiceImpl(final ForTimeRepository forTimeRepository) {
        this.forTimeRepository = forTimeRepository;
    }

    @Override
    public List<ForTimeResponse> getAll() {
        return null;
    }

    @Override
    public ForTimeResponse getById(Long id) {
        return null;
    }

    @Override
    public ForTimeResponse add(ForTimeRequest request) {
        return null;
    }

    @Override
    public ForTimeResponse edit(ForTimeRequest request) {
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
