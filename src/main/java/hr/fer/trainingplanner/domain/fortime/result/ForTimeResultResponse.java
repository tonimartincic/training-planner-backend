package hr.fer.trainingplanner.domain.fortime.result;

import lombok.Data;

@Data
public class ForTimeResultResponse {

    private Long id;

    public ForTimeResultResponse(ForTimeResult forTimeResult) {
    }
}
