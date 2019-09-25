package nz.co.kindergarten.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.kindergarten.application.builders.TeacherRegisterRequestBuilder;
import nz.co.kindergarten.application.builders.TeacherRegisterResponseBuilder;
import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeacherControllerTest extends AbstractTestController {

    private MockMvc mvc;
    private TeacherService service;
    private TeacherController controller;
    private ObjectMapper objectMapper;
    private TeacherRegisterRequest request;
    private TeacherRegisterResponse response;

    @Before
    public void setUp() {
        service = mock(TeacherService.class);
        controller = new TeacherController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
        request = new TeacherRegisterRequestBuilder().withData().build();
        response = new TeacherRegisterResponseBuilder().withData().build();
    }

    @Test
    public void testSuccessResponse() throws Exception {
        when(service.registerTeacher(request)).thenReturn(response);
        assertPositiveRequest(performPostRequest(mvc, "/teacher/register", request));
    }

    @Test
    public void assertInternalServerErrorResponse() throws Exception {
        when(service.registerTeacher(request)).thenThrow(new KinderGartenServiceException("unit.test.exception", "Unit test exception"));
        assertInternalServerError(performPostRequest(mvc, "/teacher/register", request), "unit.test.exception", "Unit test exception");
    }


}