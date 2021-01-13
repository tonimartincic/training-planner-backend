package hr.fer.trainingplanner.domain.AMRAP;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AMRAP extends Workout {

    private int minutes;

    public AMRAP() {
    }
}
