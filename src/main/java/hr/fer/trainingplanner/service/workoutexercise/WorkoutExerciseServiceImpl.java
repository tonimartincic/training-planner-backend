package hr.fer.trainingplanner.service.workoutexercise;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.exercise.*;
import hr.fer.trainingplanner.domain.workout.Workout;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseRequest;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.workoutexercise.WorkoutExerciseRepository;
import hr.fer.trainingplanner.service.exercise.ExerciseService;
import hr.fer.trainingplanner.service.workout.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    private final ExerciseService exerciseService;

    private final WorkoutService workoutService;

    private final WorkoutExerciseRepository repository;

    @Autowired
    public WorkoutExerciseServiceImpl(final ExerciseService exerciseService, final WorkoutService workoutService, final WorkoutExerciseRepository repository) {
        this.exerciseService = exerciseService;
        this.workoutService = workoutService;
        this.repository = repository;
    }

    @Override
    public List<WorkoutExercise> getAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    public WorkoutExercise getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public WorkoutExercise add(
            WorkoutExerciseRequest request,
            final long workoutId,
            final WorkoutType workoutType) {
        final WorkoutExercise entity = getEntity(request, workoutId, workoutType);
        return this.repository.save(entity);
    }

    @Override
    public WorkoutExercise edit(
            WorkoutExerciseRequest request,
            final long workoutId,
            final WorkoutType workoutType) {
        final Optional<WorkoutExercise> entityFromDatabase = this.repository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final WorkoutExercise entity = getEntity(request, workoutId, workoutType);
        return this.repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<WorkoutExerciseResponse> getResponses(final List<WorkoutExercise> entities) {
        final List<WorkoutExerciseResponse> responses = new ArrayList<>();

        for (final WorkoutExercise entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public WorkoutExerciseResponse getResponse(final WorkoutExercise entity) {
        if (entity == null) {
            return null;
        }

        WorkoutExerciseResponse response = new WorkoutExerciseResponse();

        response.setId(entity.getId());
        response.setReps(entity.getReps());
        response.setSets(entity.getSets());

        ExerciseResponse exerciseResponse = this.exerciseService.getById(entity.getExercise().getId());
        response.setExerciseName(exerciseResponse.getName());

        return response;
    }

    @Override
    public List<WorkoutExercise> getEntities(
            final List<WorkoutExerciseRequest> requests,
            final long workoutId,
            final WorkoutType workoutType) {
        final List<WorkoutExercise> entities = new ArrayList<>();

        for (final WorkoutExerciseRequest request : requests) {
            entities.add(getEntity(request, workoutId, workoutType));
        }

        return entities;
    }

    @Override
    public WorkoutExercise getEntity(
            final WorkoutExerciseRequest request,
            final long workoutId,
            final WorkoutType workoutType) {
        if (request == null) {
            return null;
        }

        WorkoutExercise entity = new WorkoutExercise();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setSets(request.getSets());
        entity.setReps(request.getReps());

        Exercise exercise = this.exerciseService.getOrCreateExerciseByName(request.getExerciseName());
        entity.setExercise(exercise);

        Workout workout = this.workoutService.getByWorkoutIdAndType(workoutId, workoutType);
        entity.setWorkout(workout);

        return entity;
    }

    @Override
    public List<WorkoutExercise> getByWorkoutIdAndType(long workoutId, WorkoutType workoutType) {
        Workout workout = this.workoutService.getByWorkoutIdAndType(workoutId, workoutType);
        return this.repository.findByWorkout(workout);
    }

    @Override
    public List<WorkoutExercise> addWorkoutExercises(long workoutId, WorkoutType workoutType, List<WorkoutExerciseRequest> workoutExerciseRequests) {
        List<WorkoutExercise> workoutExercises = new ArrayList<>();
        for (int i = 0; i < workoutExerciseRequests.size(); i++) {
            WorkoutExerciseRequest workoutExerciseRequest = workoutExerciseRequests.get(i);

            WorkoutExercise workoutExercise = null;
            if (workoutExerciseRequest.getId() == null || workoutExerciseRequest.getId() == 0) {
                workoutExercise = this.add(workoutExerciseRequest, workoutId, workoutType);
            } else {
                workoutExercise = this.edit(workoutExerciseRequest, workoutId, workoutType);
            }

            workoutExercises.add(workoutExercise);
        }

        return workoutExercises;
    }
}
