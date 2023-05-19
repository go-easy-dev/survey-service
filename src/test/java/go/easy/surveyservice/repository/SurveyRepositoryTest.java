package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.survey.AnswerEntity;
import go.easy.surveyservice.dto.survey.QuestionEntity;
import go.easy.surveyservice.dto.survey.SurveyEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
class SurveyRepositoryTest {
    @Autowired
    private SurveyRepository surveyRepository;

    @BeforeEach
    void init() {
        surveyRepository.save(SurveyEntity.builder()
                .id("TEST_ID")
                .version(1)
                .sphere("SPHERE")
                .opened(true)
                .multipleChoice(false)
                .question(QuestionEntity.builder()
                        .name("Name")
                        .possibleAnswers(List.of(AnswerEntity.builder()
                                .answerCode("Answer")
                                .build()))
                        .build())
                .build());
    }

    @Test
    void should_find_by_id() {
        // when
        var actual = surveyRepository.findAllByOpened(true);

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getId().equals("TEST_ID"))
                .hasSize(1);
    }

    @Test
    void should_find_by_sphere() {
        // when
        var actual = surveyRepository.findAllBySphere("SPHERE");

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getId().equals("TEST_ID"))
                .hasSize(1);
    }
}
