package hr.fer.trainingplanner.service.AMRAP;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.AMRAP.AMRAP;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPRequest;
import hr.fer.trainingplanner.domain.AMRAP.AMRAPResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.AMRAP.AMRAPRepository;
import hr.fer.trainingplanner.service.workoutexercise.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AMRAPServiceImpl implements AMRAPService {

    private final AMRAPRepository AMRAPRepository;

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public AMRAPServiceImpl(final AMRAPRepository AMRAPRepository, final WorkoutExerciseService workoutExerciseService) {
        this.AMRAPRepository = AMRAPRepository;
        this.workoutExerciseService = workoutExerciseService;
    }

    @Override
    public List<AMRAPResponse> getAll() {
        return getResponses(Lists.newArrayList(this.AMRAPRepository.findAll()));
    }

    @Override
    public AMRAP getById(Long id) {
        return this.AMRAPRepository.findById(id).orElseThrow();
    }

    @Override
    public AMRAPResponse add(AMRAPRequest request) {
        final AMRAP entity = getEntity(request);
        entity.setCreatedOn(LocalDate.now());
        AMRAP amrap = this.AMRAPRepository.save(entity);

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                amrap.getId(),
                WorkoutType.AMRAP,
                request.getWorkoutExerciseRequests());

        amrap.setExercises(workoutExercises);

        return getResponse(amrap);
    }

    @Override
    public AMRAPResponse edit(AMRAPRequest request) {
        final Optional<AMRAP> entityFromDatabase = this.AMRAPRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final AMRAP entity = getEntity(request);
        entity.setCreatedOn(entityFromDatabase.get().getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entity.getId(),
                WorkoutType.AMRAP,
                request.getWorkoutExerciseRequests());

        entity.setExercises(workoutExercises);

        return getResponse(this.AMRAPRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        Optional<AMRAP> entity = this.AMRAPRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }

        this.AMRAPRepository.deleteById(id);
    }

    @Override
    public List<AMRAPResponse> getResponses(final List<AMRAP> entities) {
        final List<AMRAPResponse> responses = new ArrayList<>();

        for (final AMRAP entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public AMRAPResponse getResponse(final AMRAP entity) {
        if (entity == null) {
            return null;
        }

        AMRAPResponse response = new AMRAPResponse();

        response.setId(entity.getId());
        response.setType(entity.getType().getName());
        response.setName(entity.getName());
        response.setMinutes(entity.getMinutes());
        response.setCreatedOn(entity.getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.getByWorkoutIdAndType(entity.getId(), WorkoutType.AMRAP);
        List<WorkoutExerciseResponse> workoutExerciseResponses = new ArrayList<>();
        for (WorkoutExercise workoutExercise : workoutExercises) {
            WorkoutExerciseResponse workoutExerciseResponse = this.workoutExerciseService.getResponse(workoutExercise);

            workoutExerciseResponses.add(workoutExerciseResponse);
        }

        response.setExercises(workoutExerciseResponses);

        return response;
    }

    @Override
    public List<AMRAP> getEntities(final List<AMRAPRequest> requests) {
        final List<AMRAP> entities = new ArrayList<>();

        for (final AMRAPRequest request : requests) {
            entities.add(getEntity(request));
        }

        return entities;
    }

    @Override
    public AMRAP getEntity(final AMRAPRequest request) {
        if (request == null) {
            return null;
        }

        AMRAP entity = new AMRAP();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setType(WorkoutType.AMRAP);
        entity.setName(request.getName());
        entity.setMinutes(request.getMinutes());

        return entity;
    }
}
