package nz.co.kindergarten.application.integration.tests.database;

import nz.co.kindergarten.application.database.model.Teacher;
import nz.co.kindergarten.application.integration.AbstractSpringIT;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherRepositoryTest extends AbstractSpringIT {

    @Test
    public void testTeacherRepository() {
        Teacher teacher = new Teacher()
                .active(true)
                .address("T/T, Testing St, Testing City, 1234")
                .emailId("test@kindergarten.com")
                .firstName("test")
                .lastName("test")
                .mobileNumber(6499999999L)
                .qualifications("Test");

        Teacher teacherSaved = teacherRepository.save(teacher);

        assertNotNull(teacher);
        assertNotNull(teacherSaved.id());
        assertNotNull(teacherSaved.created());
        assertNotNull(teacherSaved.modified());

        Teacher teacherByEmailAndActive = teacherRepository.findByEmailIdAndActive(teacher.emailId(), true);

        assertTrue(teacherByEmailAndActive.active());
        assertEquals(teacher.address(), teacherByEmailAndActive.address());
        assertEquals(teacher.emailId(), teacherByEmailAndActive.emailId());
        assertEquals(teacher.firstName(), teacherByEmailAndActive.firstName());
        assertEquals(teacher.lastName(), teacherByEmailAndActive.lastName());
        assertEquals(teacher.mobileNumber(), teacherByEmailAndActive.mobileNumber());
        assertEquals(teacher.qualifications(), teacherByEmailAndActive.qualifications());

        Teacher teacherActive = teacherRepository.findByIdAndActive(teacherByEmailAndActive.id(), true);

        assertNotNull(teacherActive);

        teacher.active(false);
        teacherRepository.save(teacher);

        assertNull(teacherRepository.findByEmailIdAndActive(teacher.emailId(), true));
    }

}