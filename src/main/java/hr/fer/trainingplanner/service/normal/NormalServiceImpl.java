package hr.fer.trainingplanner.service.normal;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;
import hr.fer.trainingplanner.repository.normal.NormalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NormalServiceImpl implements NormalService {

    private final NormalRepository repository;

    @Autowired
    public NormalServiceImpl(final NormalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NormalResponse> getAll() {
        return getResponses(Lists.newArrayList(this.repository.findAll()));
    }

    @Override
    public NormalResponse getById(Long id) {
        return getResponse(this.repository.findById(id));
    }

    @Override
    public NormalResponse add(NormalRequest request) {
        final Normal entity = new Normal(request);
        return getResponse(Optional.of(this.repository.save(entity)));
    }

    @Override
    public NormalResponse edit(NormalRequest request) {
        final Optional<Normal> entityFromDatabase = this.repository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Normal entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.repository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    private List<NormalResponse> getResponses(final List<Normal> entities) {
        final List<NormalResponse> responses = new ArrayList<>();

        for (final Normal entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private NormalResponse getResponse(final Optional<Normal> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new NormalResponse(entity.get());
    }
}
