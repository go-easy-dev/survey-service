
package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.scoring.UserScoreSurveyResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;

@DataMongoTest
class ScoreUserSurveyRepositoryTest {
    @Autowired
    private ScoreUserSurveyRepository scoreUserSurveyRepository;

    @BeforeEach
    void init() {
        scoreUserSurveyRepository.save(UserScoreSurveyResponse.builder()
                .userId("USER_ID")
                .createTime(LocalDateTime.now())
                .build());
    }

    @Test
    void should_find_by_id() {
        // when
        var actual = scoreUserSurveyRepository.findAllByUserId("USER_ID");

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getUserId().equals("USER_ID"))
                .hasSize(1);
    }
}
