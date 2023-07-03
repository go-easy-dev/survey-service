package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.scoring.UserScoreSurveyResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScoreUserSurveyRepository extends MongoRepository<UserScoreSurveyResponse, String> {

    List<UserScoreSurveyResponse> findAllByUserId(String userId);


}
