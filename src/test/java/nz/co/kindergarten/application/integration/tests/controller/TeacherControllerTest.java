package nz.co.kindergarten.application.integration.tests.controller;

import nz.co.kindergarten.application.builders.TeacherRegisterRequestBuilder;
import nz.co.kindergarten.application.controller.TeacherController;
import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.database.model.Teacher;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.integration.AbstractSpringIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class TeacherControllerTest extends AbstractSpringIT {

    @Autowired
    private TeacherController teacherController;

    @Test
    public void testTeacherController() throws KinderGartenServiceException {
        TeacherRegisterRequest request = new TeacherRegisterRequestBuilder().withData().build();

        TeacherRegisterResponse response = teacherController.registerTeacher(request);

        assertNotNull(response);

        Teacher teacherByEmailAndActive = teacherRepository.findByEmailIdAndActive(request.getEmailId(), true);

        assertTrue(teacherByEmailAndActive.active());
        assertEquals(request.getAddress(), teacherByEmailAndActive.address());
        assertEquals(request.getEmailId(), teacherByEmailAndActive.emailId());
        assertEquals(request.getFirstName(), teacherByEmailAndActive.firstName());
        assertEquals(request.getLastName(), teacherByEmailAndActive.lastName());
        assertEquals(Long.valueOf(request.getMobileNumber()), teacherByEmailAndActive.mobileNumber());
        assertEquals(request.getQualifications(), teacherByEmailAndActive.qualifications());

    }

}