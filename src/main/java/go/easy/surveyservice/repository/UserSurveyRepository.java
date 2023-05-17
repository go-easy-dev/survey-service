package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.userresult.bind.UserSurveyResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserSurveyRepository extends MongoRepository<UserSurveyResult, String> {

    List<UserSurveyResult> findAllByUserId(String userId);

}
