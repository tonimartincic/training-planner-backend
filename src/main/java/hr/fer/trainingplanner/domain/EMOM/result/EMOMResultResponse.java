package hr.fer.trainingplanner.domain.EMOM.result;

import lombok.Data;

@Data
public class EMOMResultResponse {

    private Long id;

    public EMOMResultResponse(EMOMResult emomResult) {
    }
}
