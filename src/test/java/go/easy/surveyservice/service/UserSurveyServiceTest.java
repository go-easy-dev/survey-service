package go.easy.surveyservice.service;

import go.easy.surveyservice.client.ScoringServiceClient;
import go.easy.surveyservice.dto.survey.AnswerEntity;
import go.easy.surveyservice.dto.survey.QuestionEntity;
import go.easy.surveyservice.dto.survey.SurveyEntity;
import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyRequest;
import go.easy.surveyservice.dto.userresult.bind.UserSurveyResult;
import go.easy.surveyservice.mapper.UserSurveyMapper;
import go.easy.surveyservice.repository.SurveyRepository;
import go.easy.surveyservice.repository.UserSurveyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserSurveyServiceTest {

    @Mock
    UserSurveyRepository userSurveyRepository;

    @Mock
    ScoringServiceClient client;

    @Mock
    SurveyRepository surveyRepository;

    @InjectMocks
    UserSurveyService userSurveyService;

    @Test
    void should_bind_survey_to_user() {
        // given
        var request = UserBindSurveyRequest.builder()
                .userId("USER_ID")
                .testId("TEST_ID")
                .testResults(Map.of("Question_ID",
                        List.of(AnswerEntity.builder()
                                .answerCode("yes")
                                .build()))
                )
                .build();

        Mockito.when(userSurveyRepository.save(Mockito.any())).thenReturn(UserSurveyMapper.INSTANCE.map(request));

        // when:
        var actual = userSurveyService.bindSurveyToUser(request);

        // then
        Assertions.assertThat(actual)
                .hasNoNullFieldsOrProperties()
                .hasFieldOrPropertyWithValue("userId", "USER_ID");
    }


    @Test
    void should_get_user_progress() {
        // given
        mockProgress();

        // when:
        var actual = userSurveyService.getUserProgress("USER_ID");

        // then
        Assertions.assertThat(actual)
                .hasFieldOrPropertyWithValue("sphereCount", 1);
        Assertions.assertThat(actual.getUserProgresses())
                .isNotEmpty()
                .filteredOn(elt -> elt.getSphereId().equals("sphere"))
                .filteredOn(elt -> Objects.nonNull(elt.getProgress()))
                .filteredOn(elt -> elt.getProgress().compareTo(BigDecimal.valueOf(100)) <= 0)
                .hasSize(1);

    }

    private void mockProgress() {

        Mockito.when(userSurveyRepository.findAllByUserId("USER_ID")).thenReturn(List.of(UserSurveyResult.builder()
                .testId("TEST_ID")
                .testResults(Map.of("Question_1",
                        List.of(AnswerEntity.builder()
                                .answerCode("yes")
                                .build()),
                        "Question_2",
                        List.of(AnswerEntity.builder()
                                .answerCode("no")
                                .build()
                        )
                ))
                .build()));
        Mockito.when(surveyRepository.findById("TEST_ID")).thenReturn(Optional.of(SurveyEntity.builder()
                .sphere("sphere")
                .questions(List.of(
                                QuestionEntity.builder().build(),
                                QuestionEntity.builder().build(),
                                QuestionEntity.builder().build()
                        )
                )
                .build()));
    }
}
