package hr.fer.trainingplanner.domain.fortime;

import hr.fer.trainingplanner.domain.workout.WorkoutRequest;
import lombok.Data;

@Data
public class ForTimeRequest extends WorkoutRequest {

    private int minutes;

    private int rounds;
}
