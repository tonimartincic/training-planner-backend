package hr.fer.trainingplanner.service.AMRAP.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResult;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultRequest;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResultResponse;
import hr.fer.trainingplanner.repository.AMRAP.result.AMRAPResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AMRAPResultServiceImpl implements AMRAPResultService {

    private final AMRAPResultRepository AMRAPResultRepository;

    @Autowired
    public AMRAPResultServiceImpl(final AMRAPResultRepository AMRAPResultRepository) {
        this.AMRAPResultRepository = AMRAPResultRepository;
    }

    @Override
    public List<AMRAPResultResponse> getAll() {
        return getResponses(Lists.newArrayList(this.AMRAPResultRepository.findAll()));
    }

    @Override
    public AMRAPResultResponse getById(final Long id) {
        return getResponse(this.AMRAPResultRepository.findById(id));
    }

    @Override
    public AMRAPResultResponse add(final AMRAPResultRequest request) {
        final AMRAPResult entity = new AMRAPResult(request);
        return getResponse(Optional.of(this.AMRAPResultRepository.save(entity)));
    }

    @Override
    public AMRAPResultResponse edit(final AMRAPResultRequest request) {
        final Optional<AMRAPResult> entityFromDatabase = this.AMRAPResultRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final AMRAPResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.AMRAPResultRepository.save(entity)));
    }

    @Override
    public void deleteById(final Long id) {
        this.AMRAPResultRepository.deleteById(id);
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
