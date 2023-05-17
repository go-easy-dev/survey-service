package go.easy.userservice.dto.scoring;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@Document("user-score-survey")
public class UserScoreSurveyResponse {
    @Id
    private String userId;

    @NotNull
    List<SphereScoring> spheresScoring;
}
