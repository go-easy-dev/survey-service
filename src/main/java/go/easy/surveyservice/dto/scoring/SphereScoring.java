package go.easy.surveyservice.dto.scoring;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SphereScoring {
    @NotBlank
    private String sphereId;

    @PositiveOrZero
    private BigDecimal scoringValue;
}
