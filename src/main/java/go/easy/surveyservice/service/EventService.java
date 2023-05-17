package go.easy.surveyservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EventService {

    private final SurveyService questionService;

    @Async
    @EventListener(value = ContextRefreshedEvent.class)
    public void upload() {
        log.info("uploading surveys start");
        questionService.uploadQuestions();
        log.info("uploading surveys finished");
    }
}
