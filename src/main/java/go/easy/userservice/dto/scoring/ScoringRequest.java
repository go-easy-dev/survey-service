package go.easy.userservice.dto.scoring;

import go.easy.userservice.dto.survey.AnswerEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class ScoringRequest {

    @NotBlank
    private String testId;

    @NotNull
    Map<String, List<AnswerEntity>> testResults;
}
