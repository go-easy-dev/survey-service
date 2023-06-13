package go.easy.surveyservice.controller;

import go.easy.surveyservice.dto.scoring.UserScoreSurveyResponse;
import go.easy.surveyservice.dto.scoring.UserScoringRequest;
import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyRequest;
import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyResponse;
import go.easy.surveyservice.dto.userresult.bind.UserSurveyResult;
import go.easy.surveyservice.dto.userresult.progress.UserProgressResponse;
import go.easy.surveyservice.service.UserSurveyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/survey/user")
@RequiredArgsConstructor
public class UserSurveyController {

    private final UserSurveyService service;

    @Operation(summary = "Связывание ответов на тест с юзером")
    @PostMapping("/bind")
    ResponseEntity<UserBindSurveyResponse> bindSurveyToUser(@RequestBody @Valid UserBindSurveyRequest request) {

        return ResponseEntity.ok(service.bindSurveyToUser(request));
    }

    @Operation(summary = "Расчет сферы по результатом теста")
    @PostMapping("/score")
    ResponseEntity<UserScoreSurveyResponse> scoreUserSurvey(@RequestBody @Valid UserScoringRequest userScoringRequest) {
        return ResponseEntity.ok(service.scoreSurvey(userScoringRequest));
    }

    @Operation(summary = "Получение прогресса теста")
    @GetMapping("/results/progress/{userId}")
    ResponseEntity<UserProgressResponse> getUserProgress(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserProgress(userId));
    }

    @Operation(summary = "Получение результатов теста по сфере")
    @GetMapping("/results/{sphereId}/{userId}")
    ResponseEntity<UserSurveyResult> getUserResults(@PathVariable String sphereId, @PathVariable String userId) {
        return ResponseEntity.ok(service.getUserResults(sphereId, userId));
    }

}
