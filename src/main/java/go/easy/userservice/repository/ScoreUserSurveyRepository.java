package go.easy.userservice.repository;

import go.easy.userservice.dto.scoring.UserScoreSurveyResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScoreUserSurveyRepository extends MongoRepository<UserScoreSurveyResponse, String> {



}
