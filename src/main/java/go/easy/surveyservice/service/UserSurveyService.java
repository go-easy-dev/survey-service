package go.easy.surveyservice.service;

import go.easy.surveyservice.client.ScoringServiceClient;
import go.easy.surveyservice.dto.scoring.ScoringRequest;
import go.easy.surveyservice.dto.scoring.UserScoreSurveyResponse;
import go.easy.surveyservice.dto.scoring.UserScoringRequest;
import go.easy.surveyservice.dto.survey.SurveyEntity;
import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyRequest;
import go.easy.surveyservice.dto.userresult.bind.UserBindSurveyResponse;
import go.easy.surveyservice.dto.userresult.bind.UserSurveyResult;
import go.easy.surveyservice.dto.userresult.progress.UserProgress;
import go.easy.surveyservice.dto.userresult.progress.UserProgressResponse;
import go.easy.surveyservice.exception.SurveyNotFoundException;
import go.easy.surveyservice.mapper.UserSurveyMapper;
import go.easy.surveyservice.repository.ScoreUserSurveyRepository;
import go.easy.surveyservice.repository.SurveyRepository;
import go.easy.surveyservice.repository.UserSurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSurveyService {

    private final UserSurveyRepository userSurveyRepository;
    private final ScoringServiceClient scoringClient;
    private final SurveyRepository surveyRepository;

    private final ScoreUserSurveyRepository scoreUserSurveyRepository;

    public UserBindSurveyResponse bindSurveyToUser(UserBindSurveyRequest userBindRequest) {
        log.info("binding survey: {} to user: {}", userBindRequest.getTestId(), userBindRequest.getUserId());
        var result = userSurveyRepository.save(UserSurveyMapper.INSTANCE.map(userBindRequest));
        return UserBindSurveyResponse.builder()
                .userId(result.getUserId())
                .bindId(result.getId())
                .build();
    }

    public UserScoreSurveyResponse scoreSurvey(UserScoringRequest request) {
        log.info("scoring users answers: {}", request);
        return userSurveyRepository.findById(request.getBindId())
                .map(result -> ScoringRequest.builder()
                        .testId(result.getTestId())
                        .testResults(result.getTestResults())
                        .build()
                )
                .map(scoringClient::scoreSpheres)
                .map(score -> UserScoreSurveyResponse.builder()
                        .spheresScoring(score.getSpheresScoring())
                        .id(UUID.randomUUID().toString())
                        .userId(request.getUserId())
                        .createTime(LocalDateTime.now())
                        .build())
                .map(scoreUserSurveyRepository::save)
                .orElseThrow(() -> new SurveyNotFoundException(HttpStatus.NOT_FOUND, "can't find user's survey by id: " + request.getBindId()));

    }

    public UserScoreSurveyResponse getUserLastScoring(String userId) {
        log.info("getting last user scoring by id: {}", userId);
        return scoreUserSurveyRepository.findAllByUserId(userId)
                .stream()
                .filter(score -> Objects.nonNull(score.getCreateTime()))
                .min(Comparator.comparing(UserScoreSurveyResponse::getCreateTime))
                .orElseThrow(() -> new SurveyNotFoundException(HttpStatus.NOT_FOUND, "can't find user's scoring by user id: " + userId));

    }

    public UserProgressResponse getUserProgress(String userId) {
        log.info("getting user progress: {}", userId);
        var userSurveys = userSurveyRepository.findAllByUserId(userId);
        return UserProgressResponse.builder()
                .userProgresses(processProgress(userSurveys))
                .sphereCount(userSurveys.size())
                .build();
    }

    public UserSurveyResult getUserResults(String sphereId, String userId) {
        return surveyRepository.findAllBySphere(sphereId)
                .stream()
                .map(SurveyEntity::getId)
                .map(testId -> userSurveyRepository.findAllByUserIdAndTestId(userId, testId))
                .flatMap(Collection::stream)
                .max(Comparator.comparing(UserSurveyResult::getCreatedAt))
                .orElseThrow(() -> new SurveyNotFoundException(HttpStatus.BAD_REQUEST, "can't find user's survey sphere: " + sphereId));
    }

    private List<UserProgress> processProgress(List<UserSurveyResult> surveyResults) {
        return surveyResults.stream()
                .map(userSurveyResult -> buildUserProgress(userSurveyResult.getTestId(), userSurveyResult.getTestResults().size()))
                .flatMap(List::stream)
                .toList();
    }

    private List<UserProgress> buildUserProgress(String testId, int questionsCount) {
        return surveyRepository.findById(testId)
                .stream()
                .filter(survey -> StringUtils.isNotBlank(survey.getSphere()))
                .filter(survey -> !CollectionUtils.isEmpty(survey.getQuestions()))
                .map(survey -> UserProgress.builder()
                        .sphereId(survey.getSphere())
                        .progress(countProgressPercents(survey.getQuestions().size(), questionsCount))
                        .build()
                ).toList();
    }

    private BigDecimal countProgressPercents(int surveysQuestionsSize, int userQuestionsSize) {
        return BigDecimal.valueOf(userQuestionsSize)
                .divide(BigDecimal.valueOf(surveysQuestionsSize), 2, RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(100));

    }


}
