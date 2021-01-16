package hr.fer.trainingplanner.service.tabata;

import com.google.common.collect.Lists;
import hr.fer.trainingplanner.domain.tabata.Tabata;
import hr.fer.trainingplanner.domain.tabata.TabataRequest;
import hr.fer.trainingplanner.domain.tabata.TabataResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import hr.fer.trainingplanner.repository.tabata.TabataRepository;
import hr.fer.trainingplanner.service.workoutexercise.WorkoutExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TabataServiceImpl implements TabataService {

    private final TabataRepository tabataRepository;

    private final WorkoutExerciseService workoutExerciseService;

    @Autowired
    public TabataServiceImpl(final TabataRepository tabataRepository, final WorkoutExerciseService workoutExerciseService) {
        this.tabataRepository = tabataRepository;
        this.workoutExerciseService = workoutExerciseService;
    }

    @Override
    public List<TabataResponse> getAll() {
        return getResponses(Lists.newArrayList(this.tabataRepository.findAll()));
    }

    @Override
    public Tabata getById(Long id) {
        return this.tabataRepository.findById(id).orElseThrow();
    }

    @Override
    public TabataResponse add(TabataRequest request) {
        final Tabata entity = getEntity(request);
        entity.setCreatedOn(LocalDate.now());
        Tabata entityFromDatabase = this.tabataRepository.save(entity);

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entityFromDatabase.getId(),
                WorkoutType.TABATA,
                request.getWorkoutExerciseRequests());

        entityFromDatabase.setExercises(workoutExercises);

        return getResponse(entityFromDatabase);
    }

    @Override
    public TabataResponse edit(TabataRequest request) {
        final Optional<Tabata> entityFromDatabase = this.tabataRepository.findById(request.getId());
        if (entityFromDatabase.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final Tabata entity = getEntity(request);
        entity.setCreatedOn(entityFromDatabase.get().getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.addWorkoutExercises(
                entity.getId(),
                WorkoutType.TABATA,
                request.getWorkoutExerciseRequests());

        entity.setExercises(workoutExercises);

        return getResponse(this.tabataRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Tabata> entity = this.tabataRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }

        this.tabataRepository.deleteById(id);
    }

    @Override
    public List<TabataResponse> getResponses(final List<Tabata> entities) {
        final List<TabataResponse> responses = new ArrayList<>();

        for (final Tabata entity : entities) {
            responses.add(getResponse(entity));
        }

        return responses;
    }

    @Override
    public TabataResponse getResponse(final Tabata entity) {
        if (entity == null) {
            return null;
        }

        TabataResponse response = new TabataResponse();

        response.setId(entity.getId());
        response.setType(entity.getType().getName());
        response.setName(entity.getName());
        response.setWork(entity.getWork());
        response.setRest(entity.getRest());
        response.setRounds(entity.getRounds());
        response.setCreatedOn(entity.getCreatedOn());

        List<WorkoutExercise> workoutExercises = this.workoutExerciseService.getByWorkoutIdAndType(entity.getId(), WorkoutType.TABATA);
        List<WorkoutExerciseResponse> workoutExerciseResponses = new ArrayList<>();
        for (WorkoutExercise workoutExercise : workoutExercises) {
            WorkoutExerciseResponse workoutExerciseResponse = this.workoutExerciseService.getResponse(workoutExercise);

            workoutExerciseResponses.add(workoutExerciseResponse);
        }

        response.setExercises(workoutExerciseResponses);

        return response;
    }

    @Override
    public List<Tabata> getEntities(List<TabataRequest> requests) {
        final List<Tabata> entities = new ArrayList<>();

        for (final TabataRequest request : requests) {
            entities.add(getEntity(request));
        }

        return entities;
    }

    @Override
    public Tabata getEntity(TabataRequest request) {
        if (request == null) {
            return null;
        }

        Tabata entity = new Tabata();

        if (request.getId() != null && request.getId() != 0) {
            entity.setId(request.getId());
        }

        entity.setType(WorkoutType.TABATA);
        entity.setName(request.getName());
        entity.setWork(request.getWork());
        entity.setRest(request.getRest());
        entity.setRounds(request.getRounds());

        return entity;
    }
}
