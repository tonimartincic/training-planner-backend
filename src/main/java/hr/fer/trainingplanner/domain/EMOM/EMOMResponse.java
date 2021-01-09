package hr.fer.trainingplanner.domain.EMOM;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class EMOMResponse extends WorkoutResponse {

    private String name;

    private int minutes;
}
