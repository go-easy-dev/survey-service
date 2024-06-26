package go.easy.surveyservice.repository;

import go.easy.surveyservice.dto.survey.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyRepository extends MongoRepository<SurveyEntity, String> {
    List<SurveyEntity> findAllByOpened(boolean isOpened);

    List<SurveyEntity> findAllBySphere(String sphere);

}
