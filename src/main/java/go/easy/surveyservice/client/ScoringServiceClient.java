package go.easy.surveyservice.client;

import go.easy.surveyservice.dto.scoring.ScoringRequest;
import go.easy.surveyservice.dto.scoring.ScoringResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "scoring-service", url = "http://localhost:8080/")
public interface ScoringServiceClient {

    @PostMapping(value = "/score")
    ScoringResponse scoreSpheres(@RequestBody @Valid ScoringRequest request);
}
