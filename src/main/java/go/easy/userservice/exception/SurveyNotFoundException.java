package go.easy.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SurveyNotFoundException extends ResponseStatusException {
	public SurveyNotFoundException(HttpStatus status, String errorMessage) {
		super(status, errorMessage);
	}

}
