package hr.fer.trainingplanner.service.normal;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.normal.Normal;
import hr.fer.trainingplanner.domain.normal.NormalRequest;
import hr.fer.trainingplanner.domain.normal.NormalResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.normal.NormalRepository;
import hr.fer.trainingplanner.service.workoutexercise.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NormalServiceImpl implements NormalService {

    private final NormalRepository repository;

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public NormalServiceImpl(final NormalRepository repository, final WorkoutExerciseService workoutExerciseService) {
        this.repository = repository;
        this.workoutExerciseService = workoutExerciseService;
    }

    @Override
    public List<NormalResponse> getAll() {
        return getResponses(Lists.newArrayList(this.repository.findAll()));
    }

    @Override
    public Normal getById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public NormalResponse add(NormalRequest request) {
        final Normal entity = getEntity(request);
        entity.setCreatedOn(LocalDate.now());
        Normal entityFromDatabase = this.repository.save(entity);

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entityFromDatabase.getId(),
                WorkoutType.NORMAL,
                request.getWorkoutExerciseRequests());

        entityFromDatabase.setExercises(workoutExercises);

        return getResponse(entityFromDatabase);
    }

    @Override
    public NormalResponse edit(NormalRequest request) {
        final Optional<Normal> entityFromDatabase = this.repository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Normal entity = getEntity(request);
        entity.setCreatedOn(entityFromDatabase.get().getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entity.getId(),
                WorkoutType.NORMAL,
                request.getWorkoutExerciseRequests());

        entity.setExercises(workoutExercises);

        return getResponse(this.repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<NormalResponse> getResponses(final List<Normal> entities) {
        final List<NormalResponse> responses = new ArrayList<>();

        for (final Normal entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public NormalResponse getResponse(final Normal entity) {
        if (entity == null) {
            return null;
        }

        NormalResponse response = new NormalResponse();

        response.setId(entity.getId());
        response.setType(entity.getType().getName());
        response.setName(entity.getName());
        response.setCreatedOn(entity.getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.getByWorkoutIdAndType(entity.getId(), WorkoutType.NORMAL);
        List<WorkoutExerciseResponse> workoutExerciseResponses = new ArrayList<>();
        for (WorkoutExercise workoutExercise : workoutExercises) {
            WorkoutExerciseResponse workoutExerciseResponse = this.workoutExerciseService.getResponse(workoutExercise);

            workoutExerciseResponses.add(workoutExerciseResponse);
        }

        response.setExercises(workoutExerciseResponses);

        return response;
    }

    @Override
    public List<Normal> getEntities(List<NormalRequest> requests) {
        final List<Normal> entities = new ArrayList<>();

        for (final NormalRequest request : requests) {
            entities.add(getEntity(request));
        }

        return entities;
    }

    @Override
    public Normal getEntity(NormalRequest request) {
        if (request == null) {
            return null;
        }

        Normal entity = new Normal();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setType(WorkoutType.NORMAL);
        entity.setName(request.getName());

        return entity;
    }
}
