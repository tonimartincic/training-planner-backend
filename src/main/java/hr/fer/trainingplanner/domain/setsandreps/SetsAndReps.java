package hr.fer.trainingplanner.domain.setsandreps;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class SetsAndReps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public SetsAndReps(SetsAndRepsRequest request) {
    }
}
