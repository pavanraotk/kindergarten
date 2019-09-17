package nz.co.kindergarten.application.controller.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class TeacherRegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String address;
    private String qualification;
}
