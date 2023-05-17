package go.easy.surveyservice.dto.scoring;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserScoringRequest {

    @NotBlank
    String userId;

    @NotBlank
    String bindId;
}
