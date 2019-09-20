package nz.co.kindergarten.application.controller;

import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableWebMvc
@RestController()
@RequestMapping("/teacher")
public class TeacherController extends AbstractKinderGartenController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/register", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public TeacherRegisterResponse registerTeacher(@Valid @RequestBody TeacherRegisterRequest teacherRegisterRequest) throws KinderGartenServiceException {
        return teacherService.registerTeacher(teacherRegisterRequest);
    }
}
