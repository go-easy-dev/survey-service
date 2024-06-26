package go.easy.surveyservice.mapper;

import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyRequest;
import org.assertj.core.api.Assertions;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

class UserSurveyMapperTest {

    private final UserSurveyMapper mapper = UserSurveyMapper.INSTANCE;
    private final EasyRandom random = new EasyRandom();

    @Test
    void should_map() {
        // given:
        var given = random.nextObject(UserBindSurveyRequest.class);

        // when:
        var actual = mapper.map(given);

        // then
        Assertions.assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdAt")
                .isEqualTo(given);
    }
}
