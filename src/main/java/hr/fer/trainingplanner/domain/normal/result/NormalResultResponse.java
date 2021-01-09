package hr.fer.trainingplanner.domain.normal.result;

import lombok.Data;

@Data
public class NormalResultResponse {

    private Long id;

    public NormalResultResponse(NormalResult normalResult) {
    }
}
