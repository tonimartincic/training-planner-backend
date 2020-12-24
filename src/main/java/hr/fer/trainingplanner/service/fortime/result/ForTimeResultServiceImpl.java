package hr.fer.trainingplanner.service.fortime.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResult;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultRequest;
import hr.fer.trainingplanner.domain.fortime.result.ForTimeResultResponse;
import hr.fer.trainingplanner.repository.fortime.result.ForTimeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForTimeResultServiceImpl implements ForTimeResultService {

    private final ForTimeResultRepository forTimeResultRepository;

    @Autowired
    public ForTimeResultServiceImpl(final ForTimeResultRepository forTimeResultRepository) {
        this.forTimeResultRepository = forTimeResultRepository;
    }

    @Override
    public List<ForTimeResultResponse> getAll() {
        return getResponses(Lists.newArrayList(this.forTimeResultRepository.findAll()));
    }

    @Override
    public ForTimeResultResponse getById(Long id) {
        return getResponse(this.forTimeResultRepository.findById(id));
    }

    @Override
    public ForTimeResultResponse add(ForTimeResultRequest request) {
        final ForTimeResult entity = new ForTimeResult(request);
        return getResponse(Optional.of(this.forTimeResultRepository.save(entity)));
    }

    @Override
    public ForTimeResultResponse edit(ForTimeResultRequest request) {
        final Optional<ForTimeResult> entityFromDatabase = this.forTimeResultRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final ForTimeResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.forTimeResultRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.forTimeResultRepository.deleteById(id);
    }

    private List<ForTimeResultResponse> getResponses(final List<ForTimeResult> entities) {
        final List<ForTimeResultResponse> responses = new ArrayList<>();

        for (final ForTimeResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private ForTimeResultResponse getResponse(final Optional<ForTimeResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new ForTimeResultResponse(entity.get());
    }
}
