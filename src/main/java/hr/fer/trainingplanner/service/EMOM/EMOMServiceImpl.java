package hr.fer.trainingplanner.service.EMOM;

import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.repository.EMOM.EMOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public EMOMResponse getById(Long id) {
        return null;
    }

    @Override
    public EMOMResponse add(EMOMRequest request) {
        return null;
    }

    @Override
    public EMOMResponse edit(EMOMRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

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
