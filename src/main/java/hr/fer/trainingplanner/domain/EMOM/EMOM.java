package hr.fer.trainingplanner.domain.EMOM;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class EMOM extends Workout {

    private int minutes;

    public EMOM() {
    }
}
