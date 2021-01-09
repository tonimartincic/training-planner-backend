package hr.fer.trainingplanner.domain.exercise;

import lombok.Data;

@Data
public class ExerciseResponse {

    private Long id;

    private String name;

    private String description;

    public ExerciseResponse() {
    }
}
