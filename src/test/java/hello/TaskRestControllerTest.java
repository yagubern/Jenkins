package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskRestController.class)
@WebAppConfiguration
public class TaskRestControllerTest {

    @InjectMocks
    private TaskRestController taskRestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskRestController).build();
    }
    @Test
    public void getUsdRate() throws Exception {

        //MvcResult result = mockMvc.perform(get(URI.create("/api/rate/USD/"))).andReturn();

        //String content = result.getResponse().getContentAsString();

        //System.out.println(content);
	mockMvc.perform(get("/api/rate/USD/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
