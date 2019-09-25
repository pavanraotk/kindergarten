package nz.co.kindergarten.application.controller;

import nz.co.kindergarten.application.controller.response.ErrorResponse;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.util.Locale.ENGLISH;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public abstract class AbstractKinderGartenController {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ErrorResponse handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String field = getFieldInException(bindingResult);
        return getError(bindingResult, field);
    }

    @ExceptionHandler(value = KinderGartenServiceException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleServiceException(KinderGartenServiceException ex) {
        return new ErrorResponse(ex.getId(), ex.getMessage());
    }

    private ErrorResponse getError(BindingResult bindingResult, String field) {
        ErrorResponse errorResponse = null;
        String[] codes = bindingResult.getFieldError().getCodes();
        for (String code : codes) {
            errorResponse = getError(field, code);
        }
        if (errorResponse == null) {
            return createError("unknown.error", "Unknown error, please contact customer care");
        }
        return errorResponse;
    }

    private ErrorResponse getError(String field, String code) {
        ErrorResponse errorResponse = null;
        switch (code.toUpperCase()) {
            case "PATTERN":
                errorResponse = createError(field + "does.not.match.pattern", messageSource.getMessage(field + "does.not.match.pattern", null, ENGLISH));
                break;
            case "NOTNULL":
            case "NOTBLANK":
                errorResponse = createError(field + "should.exist", messageSource.getMessage(field + "should.exist", null, ENGLISH));
                break;
            case "MAX":
            case "MIN":
            case "LENGTH":
                errorResponse = createError(field + "length.does.not.match", messageSource.getMessage(field + "length.does.not.match", null, ENGLISH));
                break;
        }
        return errorResponse;
    }

    private ErrorResponse createError(String code, String message) {
        return new ErrorResponse(code, message);
    }

    private String getFieldInException(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        return fieldError.getField();
    }
}
