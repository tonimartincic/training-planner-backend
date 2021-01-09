package hr.fer.trainingplanner.domain.normal.result;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class NormalResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public NormalResult() {
    }

    public NormalResult(NormalResultRequest request) {
    }
}
