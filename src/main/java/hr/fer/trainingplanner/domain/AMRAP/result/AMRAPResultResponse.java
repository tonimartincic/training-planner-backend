package hr.fer.trainingplanner.domain.AMRAP.result;

import lombok.Data;

@Data
public class AMRAPResultResponse {

    private Long id;

    public AMRAPResultResponse(AMRAPResult amrapResult) {
    }
}
