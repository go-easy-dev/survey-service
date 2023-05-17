package go.easy.userservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import go.easy.userservice.dto.survey.SurveyEntity;
import go.easy.userservice.exception.SurveyNotFoundException;
import go.easy.userservice.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final ObjectMapper objectMapper;

    public List<SurveyEntity> getPublicSurveys() {
        log.info("getting public surveys");
        return surveyRepository.findAllByOpened(true)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new SurveyNotFoundException(HttpStatus.NOT_FOUND, "can't find public surveys");
                    return result;
                }));


    }

    public List<SurveyEntity> getSurveysBySphere(String sphere) {
        log.info("getting surveys by sphere: {}", sphere);
        return surveyRepository.findAllBySphere(sphere)
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.toList(), result -> {
                    if (result.isEmpty())
                        throw new SurveyNotFoundException(HttpStatus.NOT_FOUND, "can't find survey by sphere: " + sphere);
                    return result;
                }));
    }

    public void uploadQuestions() {
        var questionsList = parseFile();
        questionsList.forEach(this::process);
    }

    private void process(SurveyEntity questionSet) {
        log.info("uploading survey: {}", questionSet.getId());
        surveyRepository.findById(questionSet.getId()).
                ifPresentOrElse(originSet -> {
                    if (questionSet.getVersion() > originSet.getVersion()) {
                        save(questionSet);
                    }
                }, () -> save(questionSet));
    }

    private void save(SurveyEntity questionSet) {
        surveyRepository.save(questionSet);
    }

    @SneakyThrows
    private List<SurveyEntity> parseFile() {
        log.info("parsing file with surveys");
        var file = new ClassPathResource("survey.json").getFile();
        return objectMapper.readValue(file,
                new TypeReference<>() {
                });
    }


}
