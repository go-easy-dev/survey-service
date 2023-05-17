package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.userresult.bind.UserSurveyResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Map;

@DataMongoTest
class UserSurveyRepositoryTest {
    @Autowired
    private UserSurveyRepository userSurveyRepository;

    @BeforeEach
    void init() {
        userSurveyRepository.save(UserSurveyResult.builder()
                .id("TEST_ID")
                .userId("USER_ID")
                .testId("TEST_ID")
                .testResults(Map.of())
                .build());
    }


    @Test
    void should_find_by_user_id() {
        // when
        var actual = userSurveyRepository.findAllByUserId("USER_ID");

        // then
        Assertions.assertThat(actual)
                .isNotEmpty()
                .filteredOn(elt -> elt.getId().equals("TEST_ID"))
                .hasSize(1);
    }
}
