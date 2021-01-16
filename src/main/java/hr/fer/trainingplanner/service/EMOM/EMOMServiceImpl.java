package hr.fer.trainingplanner.service.EMOM;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.EMOM.EMOM;
import hr.fer.trainingplanner.domain.EMOM.EMOMRequest;
import hr.fer.trainingplanner.domain.EMOM.EMOMResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.EMOM.EMOMRepository;
import hr.fer.trainingplanner.service.workoutexercise.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EMOMServiceImpl implements EMOMService {

    private final EMOMRepository EMOMRepository;

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public EMOMServiceImpl(final EMOMRepository EMOMRepository, final WorkoutExerciseService workoutExerciseService) {
        this.EMOMRepository = EMOMRepository;
        this.workoutExerciseService = workoutExerciseService;
    }

    @Override
    public List<EMOMResponse> getAll() {
        return getResponses(Lists.newArrayList(this.EMOMRepository.findAll()));
    }

    @Override
    public EMOM getById(Long id) {
        return this.EMOMRepository.findById(id).orElseThrow();
    }

    @Override
    public EMOMResponse add(EMOMRequest request) {
        final EMOM entity = getEntity(request);
        entity.setCreatedOn(LocalDate.now());
        EMOM entityFromDatabase = this.EMOMRepository.save(entity);

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entityFromDatabase.getId(),
                WorkoutType.EMOM,
                request.getWorkoutExerciseRequests());

        entityFromDatabase.setExercises(workoutExercises);

        return getResponse(entityFromDatabase);
    }

    @Override
    public EMOMResponse edit(EMOMRequest request) {
        final Optional<EMOM> entityFromDatabase = this.EMOMRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final EMOM entity = getEntity(request);
        entity.setCreatedOn(entityFromDatabase.get().getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entity.getId(),
                WorkoutType.EMOM,
                request.getWorkoutExerciseRequests());

        entity.setExercises(workoutExercises);

        return getResponse(this.EMOMRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        Optional<EMOM> entity = this.EMOMRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }

        this.EMOMRepository.deleteById(id);
    }

    @Override
    public List<EMOMResponse> getResponses(final List<EMOM> entities) {
        final List<EMOMResponse> responses = new ArrayList<>();

        for (final EMOM entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public EMOMResponse getResponse(final EMOM entity) {
        if (entity == null) {
            return null;
        }

        EMOMResponse response = new EMOMResponse();

        response.setId(entity.getId());
        response.setType(entity.getType().getName());
        response.setName(entity.getName());
        response.setMinutes(entity.getMinutes());
        response.setCreatedOn(entity.getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.getByWorkoutIdAndType(entity.getId(), WorkoutType.EMOM);
        List<WorkoutExerciseResponse> workoutExerciseResponses = new ArrayList<>();
        for (WorkoutExercise workoutExercise : workoutExercises) {
            WorkoutExerciseResponse workoutExerciseResponse = this.workoutExerciseService.getResponse(workoutExercise);

            workoutExerciseResponses.add(workoutExerciseResponse);
        }

        response.setExercises(workoutExerciseResponses);

        return response;
    }

    @Override
    public List<EMOM> getEntities(List<EMOMRequest> requests) {
        final List<EMOM> entities = new ArrayList<>();

        for (final EMOMRequest request : requests) {
            entities.add(getEntity(request));
        }

        return entities;
    }

    @Override
    public EMOM getEntity(EMOMRequest request) {
        if (request == null) {
            return null;
        }

        EMOM entity = new EMOM();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setType(WorkoutType.EMOM);
        entity.setName(request.getName());
        entity.setMinutes(request.getMinutes());

        return entity;
    }
}
