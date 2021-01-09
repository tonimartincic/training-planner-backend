package hr.fer.trainingplanner.domain.workoutexercise;

import lombok.Data;

@Data
public class WorkoutExerciseRequest {

    private Long id;

    private String exerciseName;

    private int sets;

    private int reps;
}
