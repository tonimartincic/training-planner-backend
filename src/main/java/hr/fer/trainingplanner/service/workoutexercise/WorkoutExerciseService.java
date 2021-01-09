package hr.fer.trainingplanner.service.workoutexercise;

import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseRequest;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import hr.fer.trainingplanner.enumeration.WorkoutType;

import java.util.List;

public interface WorkoutExerciseService {

    List<WorkoutExercise> getAll();

    WorkoutExercise getById(Long id);

    WorkoutExercise add(WorkoutExerciseRequest request, final long workoutId, final WorkoutType workoutType);

    WorkoutExercise edit(WorkoutExerciseRequest request, final long workoutId, final WorkoutType workoutType);

    void deleteById(Long id);

    List<WorkoutExerciseResponse> getResponses(final List<WorkoutExercise> entities);

    WorkoutExerciseResponse getResponse(final WorkoutExercise entity);

    List<WorkoutExercise> getEntities(final List<WorkoutExerciseRequest> requests, final long workoutId, final WorkoutType workoutType);

    WorkoutExercise getEntity(final WorkoutExerciseRequest request, final long workoutId, final WorkoutType workoutType);

    List<WorkoutExercise> getByWorkoutIdAndType(long workoutId, WorkoutType workoutType);

    List<WorkoutExercise> addWorkoutExercises(long workoutId, WorkoutType workoutType, List<WorkoutExerciseRequest> workoutExerciseRequests);
}
