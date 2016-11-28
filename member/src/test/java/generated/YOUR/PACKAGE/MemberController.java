package generated.YOUR.PACKAGE;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.Exception;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(
        basePackages = {"YOUR_DTOs_PACKAGE", "YOUR_SERVICEs_PACKAGE"}
)
@SpringBootTest
public class MemberController {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
            .alwaysDo(print())
            .build();}

    @Test
    public void findMember0_CHANGE_METHOD_NAME() throws Exception {
        MvcResult result = mockMvc.perform(
                get("/api/v1/members")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.YOURKEY").value("EXPECTED_VALUE")
        ).andReturn();
    }

    @Test
    public void save1_CHANGE_METHOD_NAME() throws Exception {
        MvcResult result = mockMvc.perform(
                post("/api/v1/members")
                        .contentType("application/json;charset=UTF-8")
                        .content("YOUR_JSON_STRING")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.YOURKEY").value("EXPECTED_VALUE")
        ).andReturn();
    }

    @Test
    public void save2_CHANGE_METHOD_NAME() throws Exception {
        MvcResult result = mockMvc.perform(
                put("/api/v1/members")
                        .contentType("application/json;charset=UTF-8")
                        .content("YOUR_JSON_STRING")
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$.YOURKEY").value("EXPECTED_VALUE")
        ).andReturn();
    }
}
