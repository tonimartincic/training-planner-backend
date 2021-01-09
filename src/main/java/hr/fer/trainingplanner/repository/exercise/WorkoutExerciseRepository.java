package hr.fer.trainingplanner.repository.exercise;

import hr.fer.trainingplanner.domain.workout.Workout;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutExerciseRepository extends CrudRepository<WorkoutExercise, Long> {

    List<WorkoutExercise> findByWorkout(Workout workout);
}
