package hr.fer.trainingplanner.domain.setsandreps;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SetsAndReps extends Workout {

    private String name;

    private int numberOfReps;

    private int numberOfSets;

    private int weight;

    private long exerciseId;

    public SetsAndReps() {
    }

    public SetsAndReps(SetsAndRepsRequest request) {
    }
}
