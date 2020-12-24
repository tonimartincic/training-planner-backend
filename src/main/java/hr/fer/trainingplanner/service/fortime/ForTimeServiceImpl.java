package hr.fer.trainingplanner.service.fortime;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.fortime.ForTime;
import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.repository.fortime.ForTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForTimeServiceImpl implements ForTimeService {

    private final ForTimeRepository forTimeRepository;

    @Autowired
    public ForTimeServiceImpl(final ForTimeRepository forTimeRepository) {
        this.forTimeRepository = forTimeRepository;
    }

    @Override
    public List<ForTimeResponse> getAll() {
        return getResponses(Lists.newArrayList(this.forTimeRepository.findAll()));
    }

    @Override
    public ForTimeResponse getById(Long id) {
        return getResponse(this.forTimeRepository.findById(id));
    }

    @Override
    public ForTimeResponse add(ForTimeRequest request) {
        final ForTime entity = new ForTime(request);
        return getResponse(Optional.of(this.forTimeRepository.save(entity)));
    }

    @Override
    public ForTimeResponse edit(ForTimeRequest request) {
        final Optional<ForTime> entityFromDatabase = this.forTimeRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final ForTime entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.forTimeRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.forTimeRepository.deleteById(id);
    }

    private List<ForTimeResponse> getResponses(final List<ForTime> entities) {
        final List<ForTimeResponse> responses = new ArrayList<>();

        for (final ForTime entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private ForTimeResponse getResponse(final Optional<ForTime> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new ForTimeResponse(entity.get());
    }
}
