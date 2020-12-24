package hr.fer.trainingplanner.service.tabata;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.tabata.Tabata;
import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.repository.tabata.TabataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TabataServiceImpl implements TabataService {

    private final TabataRepository tabataRepository;

    @Autowired
    public TabataServiceImpl(final TabataRepository tabataRepository) {
        this.tabataRepository = tabataRepository;
    }

    @Override
    public List<TabataResponse> getAll() {
        return getResponses(Lists.newArrayList(this.tabataRepository.findAll()));
    }

    @Override
    public TabataResponse getById(Long id) {
        return getResponse(this.tabataRepository.findById(id));
    }

    @Override
    public TabataResponse add(TabataRequest request) {
        final Tabata entity = new Tabata(request);
        return getResponse(Optional.of(this.tabataRepository.save(entity)));
    }

    @Override
    public TabataResponse edit(TabataRequest request) {
        final Optional<Tabata> entityFromDatabase = this.tabataRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Tabata entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.tabataRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.tabataRepository.deleteById(id);
    }

    private List<TabataResponse> getResponses(final List<Tabata> entities) {
        final List<TabataResponse> responses = new ArrayList<>();

        for (final Tabata entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private TabataResponse getResponse(final Optional<Tabata> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new TabataResponse(entity.get());
    }
}
