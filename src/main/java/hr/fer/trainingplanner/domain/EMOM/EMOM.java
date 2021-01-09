package hr.fer.trainingplanner.domain.EMOM;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class EMOM extends Workout {

    private String name;

    public EMOM() {
    }

    public EMOM(EMOMRequest request) {
    }
}
