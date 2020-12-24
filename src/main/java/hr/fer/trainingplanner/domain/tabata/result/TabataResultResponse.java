package hr.fer.trainingplanner.domain.tabata.result;

import lombok.Data;

@Data
public class TabataResultResponse {

    private Long id;

    public TabataResultResponse(TabataResult tabataResult) {
    }
}
