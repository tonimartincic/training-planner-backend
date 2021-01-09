package hr.fer.trainingplanner.domain.setsandreps;

import hr.fer.trainingplanner.domain.workout.WorkoutRequest;
import lombok.Data;

@Data
public class SetsAndRepsRequest extends WorkoutRequest {

    private String name;
}
