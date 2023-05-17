package go.easy.surveyservice.dto.userresult.bind;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UserBindSurveyResponse {
    @NotBlank
    private String userId;

    @NotBlank
    private String bindId;
}
