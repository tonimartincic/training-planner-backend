package hr.fer.trainingplanner.service.exercise;

import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResult;
import hr.fer.trainingplanner.domain.exercise.ExerciseRequest;
import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;
import hr.fer.trainingplanner.repository.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImpl(final ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseResponse> getAll() {
        return null;
    }

    @Override
    public ExerciseResponse getById(Long id) {
        return null;
    }

    @Override
    public ExerciseResponse add(ExerciseRequest request) {
        return null;
    }

    @Override
    public ExerciseResponse edit(ExerciseRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private List<ExerciseResponse> getResponses(final List<AMRAPResult> entities) {
        final List<ExerciseResponse> responses = new ArrayList<>();

        for (final AMRAPResult entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private ExerciseResponse getResponse(final Optional<AMRAPResult> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new ExerciseResponse(entity.get());
    }
}
