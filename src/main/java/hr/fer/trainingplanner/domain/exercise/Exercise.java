package hr.fer.trainingplanner.domain.exercise;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    public Exercise() {
    }

    public Exercise(ExerciseRequest request) {
        this.id = request.getId();
        this.name = request.getName();
        this.description = request.getDescription();
    }
}
