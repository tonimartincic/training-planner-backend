package hr.fer.trainingplanner.domain.fortime;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class ForTimeResponse extends WorkoutResponse {

    private String name;

    public ForTimeResponse(ForTime forTime) {
    }
}
