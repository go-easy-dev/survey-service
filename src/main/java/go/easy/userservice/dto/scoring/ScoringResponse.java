package go.easy.userservice.dto.scoring;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value
public class ScoringResponse {

    @NotBlank
    String testId;

    @NotNull List<SphereScoring> spheresScoring;
}
