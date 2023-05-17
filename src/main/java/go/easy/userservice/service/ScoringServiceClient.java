package go.easy.userservice.service;

import go.easy.userservice.dto.scoring.ScoringRequest;
import go.easy.userservice.dto.scoring.ScoringResponse;
import go.easy.userservice.dto.scoring.SphereScoring;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "scoring-service", url = "http://localhost:8080/")
public interface ScoringServiceClient {

    @PostMapping(value = "/score")
    ScoringResponse scoreSpheres(@RequestBody @Valid ScoringRequest request);
}
