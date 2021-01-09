package hr.fer.trainingplanner.domain.AMRAP;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import lombok.Data;

import java.util.List;

@Data
public class AMRAPResponse extends WorkoutResponse {

    private String name;

    private int minutes;

    private List<WorkoutExerciseResponse> exercises;
}
