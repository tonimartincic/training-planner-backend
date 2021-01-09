package hr.fer.trainingplanner.repository.exercise;

import hr.fer.trainingplanner.domain.exercise.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    Exercise findByName(String name);
}
