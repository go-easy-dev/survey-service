package go.easy.surveyservice.controller;

import go.easy.surveyservice.dto.survey.SurveyEntity;
import go.easy.surveyservice.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService service;

    @Operation(summary = "Получение публичных опросов")
    @GetMapping("/public")
    ResponseEntity<List<SurveyEntity>> getPublicSurveys() {
        return ResponseEntity.ok(service.getPublicSurveys());
    }

    @Operation(summary = "Получение опросов по сфере")
    @GetMapping("/sphere/{sphere}")
    ResponseEntity<List<SurveyEntity>> getSurveysBySphere(@PathVariable String sphere) {
        return ResponseEntity.ok(service.getSurveysBySphere(sphere));
    }


}
