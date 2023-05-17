package go.easy.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import go.easy.userservice.dto.survey.QuestionEntity;
import go.easy.userservice.dto.survey.SurveyEntity;
import go.easy.userservice.exception.SurveyNotFoundException;
import go.easy.userservice.repository.SurveyRepository;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    SurveyRepository surveyRepository;

    @InjectMocks
    SurveyService surveyService;

    private final EasyRandom random = new EasyRandom();

    @Test
    void should_get_public_surveys() {
        // given:
        Mockito.when(surveyRepository.findAllByOpened(true)).thenReturn(
                List.of(SurveyEntity.builder()
                        .id("ID")
                        .opened(true)
                        .question(random.nextObject(QuestionEntity.class))
                        .build())
        );

        // when:
        var actual = surveyService.getPublicSurveys();

        // then:
        Assertions.assertThat(actual)
                .isNotEmpty();
    }

    @Test
    void should_throw_public_surveys() {
        // given:
        Mockito.when(surveyRepository.findAllByOpened(true)).thenReturn(Collections.emptyList());

        // then:
        Assertions.assertThatThrownBy(() -> surveyService.getPublicSurveys())
                .isInstanceOf(SurveyNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"can't find public surveys\"");
    }

    @Test
    void should_get_surveys_by_sphere() {
        // given:
        Mockito.when(surveyRepository.findAllBySphere("sphere")).thenReturn(Collections.emptyList());

        // then:
        Assertions.assertThatThrownBy(() -> surveyService.getSurveysBySphere("sphere"))
                .isInstanceOf(SurveyNotFoundException.class)
                .hasMessage("404 NOT_FOUND \"can't find survey by sphere: sphere\"");
    }
}
