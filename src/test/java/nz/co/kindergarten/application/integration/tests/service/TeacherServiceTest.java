package nz.co.kindergarten.application.integration.tests.service;

import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.database.model.Teacher;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.integration.AbstractSpringIT;
import nz.co.kindergarten.application.service.TeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class TeacherServiceTest extends AbstractSpringIT {

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testTeacherService() throws KinderGartenServiceException {
        TeacherRegisterRequest request = new TeacherRegisterRequest();

        request.setAddress("T/T, Testing St, Testing City, 1234");
        request.setEmailId("test@kindergarten.com");
        request.setFirstName("test");
        request.setLastName("test");
        request.setMobileNumber("6499999999");
        request.setQualifications("Test");

        TeacherRegisterResponse response = teacherService.registerTeacher(request);

        assertNotNull(response);

        Teacher teacherByEmailAndActive = teacherRepository.findByEmailIdAndActive(request.getEmailId(), true);

        assertTrue(teacherByEmailAndActive.active());
        assertEquals(request.getAddress(), teacherByEmailAndActive.address());
        assertEquals(request.getEmailId(), teacherByEmailAndActive.emailId());
        assertEquals(request.getFirstName(), teacherByEmailAndActive.firstName());
        assertEquals(request.getLastName(), teacherByEmailAndActive.lastName());
        assertEquals(Long.valueOf(request.getMobileNumber()), teacherByEmailAndActive.mobileNumber());
        assertEquals(request.getQualifications(), teacherByEmailAndActive.qualifications());

        try {
            teacherService.registerTeacher(request);
            fail("Exception not thrown");
        } catch (KinderGartenServiceException ex) {
            assertEquals("teacher.already.exists", ex.getId());
            assertEquals("Active teacher already exists with this mail Id " + request.getEmailId(), ex.getMessage());
        }
    }

}