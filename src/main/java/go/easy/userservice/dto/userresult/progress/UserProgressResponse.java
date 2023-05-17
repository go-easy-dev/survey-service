package go.easy.userservice.dto.userresult.progress;

import go.easy.userservice.dto.userresult.progress.UserProgress;
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
