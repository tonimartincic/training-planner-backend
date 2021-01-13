package hr.fer.trainingplanner.domain.fortime;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ForTime extends Workout {

    private int minutes;

    private int rounds;

    public ForTime() {
    }
}
