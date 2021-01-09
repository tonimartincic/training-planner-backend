package hr.fer.trainingplanner.domain.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExercise;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutExercise> exercises;

    public Exercise() {
    }
}
