package hr.fer.trainingplanner.domain.tabata;

import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Tabata extends Workout {

    private String name;

    public Tabata() {
    }

    public Tabata(TabataRequest request) {
    }
}
