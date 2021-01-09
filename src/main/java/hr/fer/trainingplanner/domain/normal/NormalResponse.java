package hr.fer.trainingplanner.domain.normal;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class NormalResponse extends WorkoutResponse {

    private String name;

    public NormalResponse(Normal normal) {
    }
}
