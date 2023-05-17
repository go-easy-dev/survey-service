package go.easy.userservice.controller;

import go.easy.userservice.dto.survey.SurveyEntity;
import go.easy.userservice.service.SurveyService;
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

    @GetMapping("/public")
    ResponseEntity<List<SurveyEntity>> getPublicSurveys() {
        return ResponseEntity.ok(service.getPublicSurveys());
    }

    @GetMapping("sphere/{sphere}")
    ResponseEntity<List<SurveyEntity>> getSurveysBySphere(@PathVariable String sphere) {
        return ResponseEntity.ok(service.getSurveysBySphere(sphere));
    }


}
