package hr.fer.trainingplanner.domain.fortime;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class ForTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
