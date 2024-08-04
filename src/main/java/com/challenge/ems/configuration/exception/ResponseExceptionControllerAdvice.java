package com.challenge.ems.configuration.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@RestControllerAdvice(basePackages = "com.challenge.ems")
public class ResponseExceptionControllerAdvice {

    private final MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<CustomErrorMessage> exception(ResponseException exception, HttpServletRequest request) {
        String statusMessage = messageSource.getMessage(exception.getMessage(), null, Locale.getDefault());
        return ResponseEntity.status(exception.getStatusCode()).body(getCustomErrorMessageResponseEntity(request, statusMessage, formatTrace(exception)));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorMessage> handleValidationExceptions(MethodArgumentNotValidException exception, HttpServletRequest request) {
        StringBuilder message = new StringBuilder();

        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            message.append(error.getDefaultMessage()).append("-&-");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getCustomErrorMessageResponseEntity(request, String.valueOf(message), formatTrace(exception)));
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request) {
        String statusMessage = "Invalid request payload: ";

        if (exception.getCause() instanceof InvalidFormatException invalidFormatException) {
            if (invalidFormatException.getTargetType().isEnum()) {
                statusMessage += String.format("Invalid value '%s' for enum type '%s",
                        invalidFormatException.getValue(),
                        invalidFormatException.getTargetType().getSimpleName());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getCustomErrorMessageResponseEntity(request, statusMessage, formatTrace(exception)));
    }

    private CustomErrorMessage getCustomErrorMessageResponseEntity(HttpServletRequest request, String statusMessage, String trace) {
        CustomErrorMessage errorResponse = new CustomErrorMessage();
        errorResponse.setPath(getRequestPath(request));
        errorResponse.setStatus(statusMessage);
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setTrace(trace);

        return errorResponse;
    }


    private String formatTrace(Throwable exception) {
        StackTraceElement element = exception.getStackTrace()[0];
        return String.format("%s/%s/%d", element.getClassName(), element.getMethodName(), element.getLineNumber());
    }

    private String getRequestPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

//    @ResponseBody
//    @ExceptionHandler(NullPointerException.class)HH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
//2024-08-04T22:52:06.892+02:00  INFO 24060 --- [ems-rest] [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
//2024-08-04T22:52:07.863+02:00  WARN 24060 --- [ems-rest] [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
//2024-08-04T22:52:08.360+02:00  INFO 24060 --- [ems-rest] [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
//2024-08-04T22:52:08.372+02:00  INFO 24060 --- [ems-rest] [           main] com.challenge.ems.EmsRestApplication     : Started EmsRestApplication in 4.
//    public ResponseEntity<CustomErrorMessage> handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
//        CustomErrorMessage errorResponse = new CustomErrorMessage();
//        errorResponse.setPath(request.getRequestURI());
//        errorResponse.setStatus("An unexpected error occurred");
//        errorResponse.setTimestamp(LocalDateTime.now());
//        errorResponse.setLineNumber(getLineNumber(ex));
//        errorResponse.setMethodName(getMethodName(ex));
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//    }


}
