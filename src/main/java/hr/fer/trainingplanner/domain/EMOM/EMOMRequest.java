package hr.fer.trainingplanner.domain.EMOM;

import hr.fer.trainingplanner.domain.workout.WorkoutRequest;
import lombok.Data;

@Data
public class EMOMRequest extends WorkoutRequest {

    private int minutes;
}
