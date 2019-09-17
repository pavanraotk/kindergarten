package nz.co.kindergarten.application.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KinderGartenServiceException extends Exception {

    private String id;
    private String message;

    public KinderGartenServiceException(String id, String message) {
        super(message);
        this.id = id;
        this.message = message;
    }

    public KinderGartenServiceException(String id, Throwable cause) {
        super(cause);
        this.id = id;
    }

    public KinderGartenServiceException(String id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
        this.message = message;
    }


}
