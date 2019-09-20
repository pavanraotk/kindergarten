package nz.co.kindergarten.application.service;

import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.database.TeacherRepository;
import nz.co.kindergarten.application.database.model.Teacher;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public TeacherRegisterResponse registerTeacher(TeacherRegisterRequest teacherRegisterRequest) throws KinderGartenServiceException {
        Teacher teacher = createTeacher(teacherRegisterRequest);
        Teacher existingTeacher = teacherRepository.findByEmailIdAndActive(teacher.emailId(), true);
        if(existingTeacher != null) {
            logger.error("Active teacher already exists with ID {} for this email Id {}", existingTeacher.id(), existingTeacher.emailId());
            throw new KinderGartenServiceException("teacher.already.exists", "Active teacher already exists with this mail Id " + teacher.emailId());
        }
        Teacher savedTeacher = teacherRepository.save(teacher);
        return generateTeacherResponse(savedTeacher);
    }

    private TeacherRegisterResponse generateTeacherResponse(Teacher savedTeacher) {
        TeacherRegisterResponse response = new TeacherRegisterResponse();
        response.setId(savedTeacher.id());
        response.setFirstName(savedTeacher.firstName());
        response.setLastName(savedTeacher.lastName());
        response.setEmailId(savedTeacher.emailId());
        response.setMobileNumber(savedTeacher.mobileNumber());
        response.setAddress(savedTeacher.address());
        response.setQualifications(savedTeacher.qualifications());
        return response;
    }

    private Teacher createTeacher(TeacherRegisterRequest teacherRegisterRequest) {
        return new Teacher()
                .firstName(teacherRegisterRequest.getFirstName())
                .lastName(teacherRegisterRequest.getLastName())
                .emailId(teacherRegisterRequest.getEmailId())
                .mobileNumber(Long.valueOf(teacherRegisterRequest.getMobileNumber()))
                .address(teacherRegisterRequest.getAddress())
                .active(true)
                .qualifications(teacherRegisterRequest.getQualifications());
    }
}
