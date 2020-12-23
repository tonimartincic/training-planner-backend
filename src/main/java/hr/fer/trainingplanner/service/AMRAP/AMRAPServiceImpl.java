package hr.fer.trainingplanner.service.AMRAP;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.AMRAP.AMRAP;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;
import hr.fer.trainingplanner.repository.AMRAP.AMRAPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AMRAPServiceImpl implements AMRAPService {

    private final AMRAPRepository AMRAPRepository;

    @Autowired
    public AMRAPServiceImpl(final AMRAPRepository AMRAPRepository) {
        this.AMRAPRepository = AMRAPRepository;
    }

    @Override
    public List<AMRAPResponse> getAll() {
        return getResponses(Lists.newArrayList(this.AMRAPRepository.findAll()));
    }

    @Override
    public AMRAPResponse getById(Long id) {
        return null;
    }

    @Override
    public AMRAPResponse add(AMRAPRequest request) {
        return null;
    }

    @Override
    public AMRAPResponse edit(AMRAPRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<AMRAPResponse> getResponses(final List<AMRAP> entities) {
        final List<AMRAPResponse> responses = new ArrayList<>();

        for (final AMRAP entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private AMRAPResponse getResponse(final Optional<AMRAP> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new AMRAPResponse(entity.get());
    }
}
