package hr.fer.trainingplanner.domain.workout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import hr.fer.trainingplanner.enumeration.WorkoutType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="discriminator", discriminatorType=DiscriminatorType.STRING)
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private WorkoutType type;

    @JsonIgnore
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkoutExercise> exercises;
}
