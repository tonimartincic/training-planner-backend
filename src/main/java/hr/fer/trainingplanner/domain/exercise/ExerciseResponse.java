package hr.fer.trainingplanner.domain.exercise;

import hr.fer.trainingplanner.domain.AMRAP.result.AMRAPResult;
import lombok.Data;

@Data
public class ExerciseResponse {

    private Long id;

    public ExerciseResponse(AMRAPResult amrapResult) {
    }
}
