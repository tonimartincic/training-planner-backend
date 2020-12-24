package hr.fer.trainingplanner.service.EMOM.result;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResult;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultRequest;
import hr.fer.trainingplanner.domain.EMOM.result.EMOMResultResponse;
import hr.fer.trainingplanner.repository.EMOM.result.EMOMResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return getResponses(Lists.newArrayList(this.EMOMResultRepository.findAll()));
    }

    @Override
    public EMOMResultResponse getById(Long id) {
        return getResponse(this.EMOMResultRepository.findById(id));
    }

    @Override
    public EMOMResultResponse add(EMOMResultRequest request) {
        final EMOMResult entity = new EMOMResult(request);
        return getResponse(Optional.of(this.EMOMResultRepository.save(entity)));
    }

    @Override
    public EMOMResultResponse edit(EMOMResultRequest request) {
        final Optional<EMOMResult> entityFromDatabase = this.EMOMResultRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final EMOMResult entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.EMOMResultRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.EMOMResultRepository.deleteById(id);
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
