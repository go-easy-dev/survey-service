package go.easy.surveyservice.dto.userresult.progress;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder()
public class UserProgressResponse {

    @NotNull
    private List<UserProgress> userProgresses;

    private int sphereCount;
}
