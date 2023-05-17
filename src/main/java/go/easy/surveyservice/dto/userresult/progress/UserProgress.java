package go.easy.surveyservice.dto.userresult.progress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserProgress {

    @NotBlank
    String sphereId;

    @PositiveOrZero
    BigDecimal progress;
}
