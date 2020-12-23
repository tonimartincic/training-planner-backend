package hr.fer.trainingplanner.domain.fortime.result;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ForTimeResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
