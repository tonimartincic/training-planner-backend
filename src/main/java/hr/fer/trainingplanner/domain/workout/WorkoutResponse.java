package hr.fer.trainingplanner.domain.workout;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseResponse;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class WorkoutResponse {

    private Long id;

    private String name;

    private String type;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdOn;

    private List<WorkoutExerciseResponse> exercises;
}
