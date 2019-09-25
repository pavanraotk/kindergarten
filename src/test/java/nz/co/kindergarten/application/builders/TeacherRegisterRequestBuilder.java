package nz.co.kindergarten.application.builders;

import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;

public class TeacherRegisterRequestBuilder {

    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
    private String address;
    private String qualifications;

    public TeacherRegisterRequestBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TeacherRegisterRequestBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TeacherRegisterRequestBuilder setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public TeacherRegisterRequestBuilder setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public TeacherRegisterRequestBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public TeacherRegisterRequestBuilder setQualifications(String qualifications) {
        this.qualifications = qualifications;
        return this;
    }

    public TeacherRegisterRequest build() {
        return new TeacherRegisterRequest(firstName, lastName, emailId, mobileNumber, address, qualifications);
    }

    public TeacherRegisterRequestBuilder withData() {
        this.address = "T/T, Testing St, Testing City, 1234";
        this.emailId = "test@kindergarten.com";
        this.firstName = "test";
        this.lastName = "test";
        this.mobileNumber = "6499999999";
        this.qualifications = "Test";
        return this;
    }


}
