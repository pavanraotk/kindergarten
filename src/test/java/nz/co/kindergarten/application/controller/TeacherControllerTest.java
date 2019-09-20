package nz.co.kindergarten.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.kindergarten.application.controller.request.TeacherRegisterRequest;
import nz.co.kindergarten.application.controller.response.TeacherRegisterResponse;
import nz.co.kindergarten.application.exception.KinderGartenServiceException;
import nz.co.kindergarten.application.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.apache.commons.lang.math.RandomUtils.nextLong;

public class TeacherControllerTest {

    private MockMvc mvc;
    private TeacherService service;
    private TeacherController controller;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        service = mock(TeacherService.class);
        controller = new TeacherController(service);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSuccess() throws Exception {
        TeacherRegisterRequest request = new TeacherRegisterRequest();
        request.setAddress("T/T, Testing St, Testing City, 1234");
        request.setEmailId("test@kindergarten.com");
        request.setFirstName("test");
        request.setLastName("test");
        request.setMobileNumber("6499999999");
        request.setQualifications("Test");

        TeacherRegisterResponse teacherRegisterResponse = new TeacherRegisterResponse();
        teacherRegisterResponse.setId(nextLong());
        teacherRegisterResponse.setQualifications("Test");
        teacherRegisterResponse.setAddress("T/T, Testing St, Testing City, 1234");
        teacherRegisterResponse.setEmailId("test@kindergarten.com");
        teacherRegisterResponse.setFirstName("test");
        teacherRegisterResponse.setLastName("test");
        teacherRegisterResponse.setMobileNumber(6499999999L);


        when(service.registerTeacher(request)).thenReturn(teacherRegisterResponse);

        mvc.perform(post("/teacher/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String getJsonString(Object request) throws JsonProcessingException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        return jsonRequest;
    }

}