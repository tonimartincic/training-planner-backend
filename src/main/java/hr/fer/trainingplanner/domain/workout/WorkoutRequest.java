package hr.fer.trainingplanner.domain.workout;

import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseRequest;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkoutRequest {

    private Long id;

    private List<WorkoutExerciseRequest> workoutExerciseRequests;

    public WorkoutRequest() {
        this.workoutExerciseRequests = new ArrayList<>();
    }
}
