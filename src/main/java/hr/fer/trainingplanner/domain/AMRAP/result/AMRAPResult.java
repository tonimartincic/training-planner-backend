package hr.fer.trainingplanner.domain.AMRAP.result;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AMRAPResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AMRAPResult() {
    }

    public AMRAPResult(AMRAPResultRequest request) {
    }
}
