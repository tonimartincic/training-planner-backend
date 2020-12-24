package hr.fer.trainingplanner.service.EMOM;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.repository.EMOM.EMOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EMOMServiceImpl implements EMOMService {

    private final EMOMRepository EMOMRepository;

    @Autowired
    public EMOMServiceImpl(final EMOMRepository EMOMRepository) {
        this.EMOMRepository = EMOMRepository;
    }

    @Override
    public List<EMOMResponse> getAll() {
        return getResponses(Lists.newArrayList(this.EMOMRepository.findAll()));
    }

    @Override
    public EMOMResponse getById(Long id) {
        return getResponse(this.EMOMRepository.findById(id));
    }

    @Override
    public EMOMResponse add(EMOMRequest request) {
        final EMOM entity = new EMOM(request);
        return getResponse(Optional.of(this.EMOMRepository.save(entity)));
    }

    @Override
    public EMOMResponse edit(EMOMRequest request) {
        final Optional<EMOM> entityFromDatabase = this.EMOMRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final EMOM entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.EMOMRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.EMOMRepository.deleteById(id);
    }

    private List<EMOMResponse> getResponses(final List<EMOM> entities) {
        final List<EMOMResponse> responses = new ArrayList<>();

        for (final EMOM entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private EMOMResponse getResponse(final Optional<EMOM> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new EMOMResponse(entity.get());
    }
}
