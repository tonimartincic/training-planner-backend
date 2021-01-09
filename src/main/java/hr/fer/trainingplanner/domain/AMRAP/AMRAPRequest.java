package hr.fer.trainingplanner.domain.AMRAP;

import hr.fer.trainingplanner.domain.workout.WorkoutRequest;
import lombok.Data;

@Data
public class AMRAPRequest extends WorkoutRequest {

    private String name;

    private int minutes;
}
