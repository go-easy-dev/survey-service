package go.easy.userservice.repository;

import go.easy.userservice.dto.userresult.bind.UserSurveyResult;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserSurveyRepository extends MongoRepository<UserSurveyResult, String> {

    List<UserSurveyResult> findAllByUserId(String userId);

}
