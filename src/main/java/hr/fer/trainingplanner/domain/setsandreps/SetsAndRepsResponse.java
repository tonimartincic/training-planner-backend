package hr.fer.trainingplanner.domain.setsandreps;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class SetsAndRepsResponse extends WorkoutResponse {

    private String name;

    public SetsAndRepsResponse(SetsAndReps setsAndReps) {
    }
}
