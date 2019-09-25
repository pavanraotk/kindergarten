package nz.co.kindergarten.application.controller.response;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String address;
    private String qualifications;
}
