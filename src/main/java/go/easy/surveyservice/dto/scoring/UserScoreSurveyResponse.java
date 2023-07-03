package go.easy.surveyservice.dto.scoring;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document("userScoreSurvey")
public class UserScoreSurveyResponse {
    @Id
    private String id;

    @Indexed
    private String userId;

    @NotNull
    private List<SphereScoring> spheresScoring;

    private LocalDateTime createTime;
}
