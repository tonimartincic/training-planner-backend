package hr.fer.trainingplanner.domain.exercise;

import lombok.Data;

@Data
public class ExerciseRequest {

    private Long id;

    private String name;

    private String description;
}
