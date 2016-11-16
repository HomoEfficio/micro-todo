package homo.efficio.toy.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homo.efficio.toy.member.dto.MemberDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hanmomhanda on 2016-11-13.
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {
        "homo.efficio.toy.member.service",
        "homo.efficio.toy.member.dto"
})
@SpringBootTest
public class MemberIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .alwaysDo(print())
                .build();
    }

    @Test
    public void 회원등록() throws Exception {

        String userName = "hanmomhanda";
        String email = "hanmomhanda@gmail.com";
        MemberDto memberDto = new MemberDto();
        memberDto.setUserName(userName);
        memberDto.setEmail(email);

        String memberDtoJson = objectMapper.writeValueAsString(memberDto);

        MvcResult result = mockMvc
                .perform(
                        post("/api/v1/members")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(memberDtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(userName)))
                .andExpect(jsonPath("$.email", is(email)))
                .andReturn();
    }
}
