package nz.co.kindergarten.application.controller;

import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends AbstractKinderGartenController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/register", method = POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<TeacherRegisterResponse> registerTeacher(@Valid @RequestBody TeacherRegisterRequest teacherRegisterRequest) throws KinderGartenServiceException {
        return new ResponseEntity<>(teacherService.registerTeacher(teacherRegisterRequest), HttpStatus.OK);
    }
}
