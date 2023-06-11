package pet.store.controller.error;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	/* private enum LogStatus{
		STACK_TRACE, MESSAGE_ONLY
	}
	
	@Data
	private class ExceptionMessage{
		private String message;
		private String statusReason;
		private int statusCode;
		private String timeStamp;
		private String uri;
	}
	*/ 
	
	// Returns No such element exception
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoSuchElementException(NoSuchElementException ex){
		log.info("No such element exception: {}", ex.toString());
		Map<String, String> message = new HashMap<>();
		message.put("message", ex.toString());
		return message; 
	}
		
}
