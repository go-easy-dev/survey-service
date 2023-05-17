package go.easy.userservice.dto.userresult.bind;

import go.easy.userservice.dto.survey.AnswerEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserBindSurveyRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String testId;

    @NotNull
    Map<String, List<AnswerEntity>> testResults;
}
