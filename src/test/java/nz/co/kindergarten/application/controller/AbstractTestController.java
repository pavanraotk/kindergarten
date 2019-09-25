package nz.co.kindergarten.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.kindergarten.application.controller.response.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class AbstractTestController {

    private static ObjectMapper objectMapper = new ObjectMapper();

    MvcResult performPostRequest(MockMvc mvc, String path, Object request) throws Exception {
            return mvc.perform(post(path)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(getJsonString(request))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
    }

    void assertPositiveRequest(MvcResult mvcResult) {
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
    }

    void assertInternalServerError(MvcResult mvcResult, String code, String message) throws Exception {
        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(500);
        ErrorResponse errorResponse = (ErrorResponse) getObjectFromString(mvcResult.getResponse().getContentAsString(), ErrorResponse.class);
        assertThat(errorResponse.getCode()).isEqualTo(code);
        assertThat(errorResponse.getMessage()).isEqualTo(message);

    }

    Object getObjectFromString(String responseString, Class className) throws IOException {
        return objectMapper.readValue(responseString, className);
    }

    String getJsonString(Object request) throws JsonProcessingException {
        return objectMapper.writeValueAsString(request);
    }
}
