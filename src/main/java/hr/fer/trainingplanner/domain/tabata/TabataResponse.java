package hr.fer.trainingplanner.domain.tabata;

import hr.fer.trainingplanner.domain.workout.WorkoutResponse;
import lombok.Data;

@Data
public class TabataResponse extends WorkoutResponse {

    private String name;

    public TabataResponse(Tabata tabata) {
    }
}
