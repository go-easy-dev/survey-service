package go.easy.userservice.repository;

import go.easy.userservice.dto.survey.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyRepository extends MongoRepository<SurveyEntity, String> {
    List<SurveyEntity> findAllByOpened(boolean isOpened);

    List<SurveyEntity> findAllBySphere(String sphere);

}
