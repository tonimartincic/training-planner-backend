package hr.fer.trainingplanner.service.fortime;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.fortime.ForTime;
import hr.fer.trainingplanner.domain.fortime.ForTimeRequest;
import hr.fer.trainingplanner.domain.fortime.ForTimeResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.fortime.ForTimeRepository;
import hr.fer.trainingplanner.service.workoutexercise.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForTimeServiceImpl implements ForTimeService {

    private final ForTimeRepository forTimeRepository;

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public ForTimeServiceImpl(final ForTimeRepository forTimeRepository, final WorkoutExerciseService workoutExerciseService) {
        this.forTimeRepository = forTimeRepository;
        this.workoutExerciseService = workoutExerciseService;
    }

    @Override
    public List<ForTimeResponse> getAll() {
        return getResponses(Lists.newArrayList(this.forTimeRepository.findAll()));
    }

    @Override
    public ForTime getById(Long id) {
        return this.forTimeRepository.findById(id).orElseThrow();
    }

    @Override
    public ForTimeResponse add(ForTimeRequest request) {
        final ForTime entity = getEntity(request);
        entity.setCreatedOn(LocalDate.now());
        ForTime entityFromDatabase = this.forTimeRepository.save(entity);

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entityFromDatabase.getId(),
                WorkoutType.FOR_TIME,
                request.getWorkoutExerciseRequests());

        entityFromDatabase.setExercises(workoutExercises);

        return getResponse(entityFromDatabase);
    }

    @Override
    public ForTimeResponse edit(ForTimeRequest request) {
        final Optional<ForTime> entityFromDatabase = this.forTimeRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final ForTime entity = getEntity(request);
        entity.setCreatedOn(entityFromDatabase.get().getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entity.getId(),
                WorkoutType.FOR_TIME,
                request.getWorkoutExerciseRequests());

        entity.setExercises(workoutExercises);

        return getResponse(this.forTimeRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        Optional<ForTime> entity = this.forTimeRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }

        this.forTimeRepository.deleteById(id);
    }

    @Override
    public List<ForTimeResponse> getResponses(final List<ForTime> entities) {
        final List<ForTimeResponse> responses = new ArrayList<>();

        for (final ForTime entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public ForTimeResponse getResponse(final ForTime entity) {
        if (entity == null) {
            return null;
        }

        ForTimeResponse response = new ForTimeResponse();

        response.setId(entity.getId());
        response.setType(entity.getType().getName());
        response.setName(entity.getName());
        response.setMinutes(entity.getMinutes());
        response.setRounds(entity.getRounds());
        response.setCreatedOn(entity.getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.getByWorkoutIdAndType(entity.getId(), WorkoutType.FOR_TIME);
        List<WorkoutExerciseResponse> workoutExerciseResponses = new ArrayList<>();
        for (WorkoutExercise workoutExercise : workoutExercises) {
            WorkoutExerciseResponse workoutExerciseResponse = this.workoutExerciseService.getResponse(workoutExercise);

            workoutExerciseResponses.add(workoutExerciseResponse);
        }

        response.setExercises(workoutExerciseResponses);

        return response;
    }

    @Override
    public List<ForTime> getEntities(List<ForTimeRequest> requests) {
        final List<ForTime> entities = new ArrayList<>();

        for (final ForTimeRequest request : requests) {
            entities.add(getEntity(request));
        }

        return entities;
    }

    @Override
    public ForTime getEntity(ForTimeRequest request) {
        if (request == null) {
            return null;
        }

        ForTime entity = new ForTime();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setType(WorkoutType.FOR_TIME);
        entity.setName(request.getName());
        entity.setMinutes(request.getMinutes());
        entity.setRounds(request.getRounds());

        return entity;
    }
}
