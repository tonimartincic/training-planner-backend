package hr.fer.trainingplanner.service.workout;

import hr.fer.trainingplanner.domain.workout.Workout;
import hr.fer.trainingplanner.enumeration.WorkoutType;

import java.util.List;

public interface WorkoutService {

    List<Workout> getAll();

    Workout getByWorkoutIdAndType(long workoutId, WorkoutType workoutType);
}
