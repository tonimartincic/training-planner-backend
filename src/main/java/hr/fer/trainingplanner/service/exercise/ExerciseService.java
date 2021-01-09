package hr.fer.trainingplanner.service.exercise;

import hr.fer.trainingplanner.domain.exercise.Exercise;
import hr.fer.trainingplanner.domain.exercise.ExerciseRequest;
import hr.fer.trainingplanner.domain.exercise.ExerciseResponse;

import java.util.List;

public interface ExerciseService {
    List<ExerciseResponse> getAll();

    ExerciseResponse getById(Long id);

    ExerciseResponse add(ExerciseRequest request);

    ExerciseResponse edit(ExerciseRequest request);

    void deleteById(Long id);

    Exercise getOrCreateExerciseByName(String name);
}
