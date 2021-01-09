package hr.fer.trainingplanner.domain.workoutexercise;

import hr.fer.trainingplanner.domain.exercise.Exercise;
import hr.fer.trainingplanner.domain.workout.Workout;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    private int sets;

    private int reps;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;
}
