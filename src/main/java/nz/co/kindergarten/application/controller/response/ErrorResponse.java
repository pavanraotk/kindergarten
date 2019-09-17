package nz.co.kindergarten.application.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class ErrorResponse {
    String code;
    String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
