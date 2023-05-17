package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.scoring.UserScoreSurveyResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreUserSurveyRepository extends MongoRepository<UserScoreSurveyResponse, String> {



}
