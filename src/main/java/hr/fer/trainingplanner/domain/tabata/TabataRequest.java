package hr.fer.trainingplanner.domain.tabata;

import hr.fer.trainingplanner.domain.workout.WorkoutRequest;
import lombok.Data;

@Data
public class TabataRequest extends WorkoutRequest {

    private String name;
}
