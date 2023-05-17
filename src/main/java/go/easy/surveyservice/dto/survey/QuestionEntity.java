package go.easy.surveyservice.dto.survey;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionEntity {
    @NotBlank
    private String name;
    private String id;
    private List<AnswerEntity> possibleAnswers;
}
