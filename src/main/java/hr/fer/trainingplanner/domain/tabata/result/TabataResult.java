package hr.fer.trainingplanner.domain.tabata.result;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TabataResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public TabataResult() {
    }

    public TabataResult(TabataResultRequest request) {
    }
}
