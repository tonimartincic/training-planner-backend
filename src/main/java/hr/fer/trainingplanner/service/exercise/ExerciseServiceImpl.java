package hr.fer.trainingplanner.service.exercise;

import com.google.common.collect.Lists;
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
        return getResponse(this.exerciseRepository.findById(id).orElseThrow());
    }

    @Override
    public ExerciseResponse add(ExerciseRequest request) {
        final Exercise entity = getEntity(request);
        return getResponse(this.exerciseRepository.save(entity));
    }

    @Override
    public ExerciseResponse edit(ExerciseRequest request) {
        final Optional<Exercise> entityFromDatabase = this.exerciseRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Exercise entity = entityFromDatabase.get();

        entity.setName(request.getName());
        entity.setDescription(request.getDescription());

        return getResponse(this.exerciseRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Exercise> entity = this.exerciseRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }

        this.exerciseRepository.deleteById(id);
    }

    @Override
    public Exercise getOrCreateExerciseByName(String name) {
        Exercise exercise = this.exerciseRepository.findByName(name);
        if (exercise != null) {
            return exercise;
        }

        Exercise newExercise = new Exercise();
        newExercise.setName(name);

        newExercise = this.exerciseRepository.save(newExercise);
        return newExercise;
    }

    public List<ExerciseResponse> getResponses(final List<Exercise> entities) {
        final List<ExerciseResponse> responses = new ArrayList<>();

        for (final Exercise entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    public ExerciseResponse getResponse(final Exercise entity) {
        if (entity == null) {
            return null;
        }

        ExerciseResponse exerciseResponse = new ExerciseResponse();

        exerciseResponse.setId(entity.getId());
        exerciseResponse.setName(entity.getName());
        exerciseResponse.setDescription(entity.getDescription());

        return exerciseResponse;
    }

    public List<Exercise> getEntities(final List<ExerciseRequest> exerciseRequests) {
        final List<Exercise> exercises = new ArrayList<>();

        for (final ExerciseRequest exerciseRequest : exerciseRequests) {
            exercises.add(getEntity(exerciseRequest));
        }

        return exercises;
    }

    public Exercise getEntity(final ExerciseRequest exerciseRequest) {
        if (exerciseRequest == null) {
            return null;
        }

        Exercise exercise = new Exercise();

        exercise.setId(exerciseRequest.getId());
        exercise.setName(exerciseRequest.getName());
        exercise.setDescription(exerciseRequest.getDescription());

        return exercise;
    }
}
