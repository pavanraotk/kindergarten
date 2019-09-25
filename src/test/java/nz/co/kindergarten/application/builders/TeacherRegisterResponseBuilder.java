package nz.co.kindergarten.application.builders;

import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils;

import static org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils.nextLong;

public class TeacherRegisterResponseBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private Long mobileNumber;
    private String address;
    private String qualifications;

    public TeacherRegisterResponseBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public TeacherRegisterResponseBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TeacherRegisterResponseBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TeacherRegisterResponseBuilder setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public TeacherRegisterResponseBuilder setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public TeacherRegisterResponseBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public TeacherRegisterResponseBuilder setQualifications(String qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public TeacherRegisterResponseBuilder withData() {
        this.id = nextLong();
        this.address = "T/T, Testing St, Testing City, 1234";
        this.emailId = "test@kindergarten.com";
        this.firstName = "test";
        this.lastName = "test";
        this.mobileNumber = 6499999999L;
        this.qualifications = "Test";
        return this;
    }

    public TeacherRegisterResponse build() {
        return new TeacherRegisterResponse(id, firstName, lastName, emailId, mobileNumber, address, qualifications);
    }
}
