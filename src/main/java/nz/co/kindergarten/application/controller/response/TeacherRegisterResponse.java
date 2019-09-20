package nz.co.kindergarten.application.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Getter
@Setter
public class TeacherRegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String address;
    private String qualifications;
}
