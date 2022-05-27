package application.exceptionHandling;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ErrorHandlingControllerAdvice {
  
  @ExceptionHandler({BadRequestException.class})
	public ResponseEntity<String> exceptionHandler(RuntimeException ex, HttpServletRequest request){
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
  
  @ExceptionHandler(value = { ConstraintViolationException.class })
  public ResponseEntity<List<String>> handleParamException(ConstraintViolationException exception) {
		
		List<String> result = exception.getConstraintViolations().stream()
						.map((e)-> e.getMessage()+": " + e.getInvalidValue())
						.collect(Collectors.toList());
		
		return new ResponseEntity<List<String>>(result, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(value = {MissingServletRequestParameterException.class })
  public ResponseEntity<String> handleBodyException(MissingServletRequestParameterException exception) {
	    
	  String msg = "Parameter " + exception.getParameterName() +" required";
		
	  return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
  }
}