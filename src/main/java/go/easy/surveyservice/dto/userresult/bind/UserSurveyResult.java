package go.easy.surveyservice.dto.userresult.bind;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Document(collection = "user-survey-result")
public class UserSurveyResult extends UserBindSurveyRequest {

    @Id
    private String id;

    private LocalDateTime createdAt;

}
