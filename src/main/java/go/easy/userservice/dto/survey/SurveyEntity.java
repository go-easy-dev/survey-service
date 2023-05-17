package go.easy.userservice.dto.survey;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "survey")
public class SurveyEntity {
    @Id
    private String id;

    @NotNull
    private long version;

    private String sphere;

    @NotNull
    private boolean multipleChoice;

    @NotNull
    @Singular
    private List<QuestionEntity> questions;

    private boolean opened;


}
