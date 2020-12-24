package hr.fer.trainingplanner.service.exercise;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResult;
import hr.fer.trainingplanner.domain.exercise.Exercise;
import hr.fer.trainingplanner.domain.exercise.ExerciseRequest;
import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;
import hr.fer.trainingplanner.repository.exercise.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return getResponses(Lists.newArrayList(this.exerciseRepository.findAll()));
    }

    @Override
    public ExerciseResponse getById(Long id) {
        return getResponse(this.exerciseRepository.findById(id));
    }

    @Override
    public ExerciseResponse add(ExerciseRequest request) {
        final Exercise entity = new Exercise(request);
        return getResponse(Optional.of(this.exerciseRepository.save(entity)));
    }

    @Override
    public ExerciseResponse edit(ExerciseRequest request) {
        final Optional<Exercise> entityFromDatabase = this.exerciseRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Exercise entity = entityFromDatabase.get();

        // TODO: update data

        return getResponse(Optional.of(this.exerciseRepository.save(entity)));
    }

    @Override
    public void deleteById(Long id) {
        this.exerciseRepository.deleteById(id);
    }

    private List<ExerciseResponse> getResponses(final List<Exercise> entities) {
        final List<ExerciseResponse> responses = new ArrayList<>();

        for (final Exercise entity : entities) {
            responses.add(getResponse(Optional.ofNullable(entity)));
        }

        return responses;
    }

    private ExerciseResponse getResponse(final Optional<Exercise> entity) {
        if (entity.isEmpty()) {
            return null;
        }

        return new ExerciseResponse(entity.get());
    }
}
