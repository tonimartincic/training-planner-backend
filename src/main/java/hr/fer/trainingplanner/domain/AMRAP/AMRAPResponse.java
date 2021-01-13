package hr.fer.trainingplanner.domain.AMRAP;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class AMRAPResponse extends WorkoutResponse {

    private int minutes;
}
