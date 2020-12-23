package hr.fer.trainingplanner.service.EMOM.result;

import hr.fer.trainingplanner.domain.EMOM.result.EMOMResult;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultRequest;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultResponse;
import hr.fer.trainingplanner.repository.EMOM.result.EMOMResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EMOMResultServiceImpl implements EMOMResultService {

    private final EMOMResultRepository EMOMResultRepository;

    @Autowired
    public EMOMResultServiceImpl(final EMOMResultRepository EMOMResultRepository) {
        this.EMOMResultRepository = EMOMResultRepository;
    }

    @Override
    public List<EMOMResultResponse> getAll() {
        return null;
    }

    @Override
    public EMOMResultResponse getById(Long id) {
        return null;
    }

    @Override
    public EMOMResultResponse add(EMOMResultRequest request) {
        return null;
    }

    @Override
    public EMOMResultResponse edit(EMOMResultRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<EMOMResultResponse> getResponses(final List<EMOMResult> entities) {
        final List<EMOMResultResponse> responses = new ArrayList<>();

        for (final EMOMResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private EMOMResultResponse getResponse(final Optional<EMOMResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new EMOMResultResponse(entity.get());
    }
}
