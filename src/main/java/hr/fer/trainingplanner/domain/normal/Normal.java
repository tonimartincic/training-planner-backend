package hr.fer.trainingplanner.domain.normal;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Normal extends Workout {

    private String name;

    public Normal() {
    }

    public Normal(NormalRequest request) {
    }
}
