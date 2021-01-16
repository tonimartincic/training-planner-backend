package hr.fer.trainingplanner.domain.workout;

import com.fasterxml.jackson.annotation.JsonFormat;
import hr.fer.trainingplanner.domain.workoutexercise.WorkoutExerciseRequest;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class WorkoutRequest {

    private Long id;

    private String name;

    private List<WorkoutExerciseRequest> workoutExerciseRequests;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public WorkoutRequest() {
        this.workoutExerciseRequests = new ArrayList<>();
    }
}
