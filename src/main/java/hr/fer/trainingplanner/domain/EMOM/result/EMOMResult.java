package hr.fer.trainingplanner.domain.EMOM.result;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class EMOMResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public EMOMResult() {
    }

    public EMOMResult(EMOMResultRequest request) {
    }
}
