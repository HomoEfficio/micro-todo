package homo.efficio.micro.todo.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import homo.efficio.micro.todo.member.dto.MemberDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author homo.efficio@gmail.com
 *         created on 2016-11-16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

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

    @Ignore  // BindException이 왜 assert 실패인지 모르겠음
    @Test(expected = BindException.class)
    public void 회원등록_userName_길이부족_잘못된사례1() throws Exception {

        MemberDto memberDto = getShortUserNameMemberDto();

        String memberDtoJson = objectMapper.writeValueAsString(memberDto);

        MvcResult result = mockMvc
                .perform(
                        post("/api/v1/members")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(memberDtoJson))
                .andReturn();
    }

    @Ignore  // BindException이 왜 assert 실패인지 모르겠음
    @Test
    public void 회원등록_userName_길이부족_잘못된사례2() throws Exception {

        thrown.expect(BindException.class);

        MemberDto memberDto = getShortUserNameMemberDto();

        String memberDtoJson = objectMapper.writeValueAsString(memberDto);

        MvcResult result = mockMvc
                .perform(
                        post("/api/v1/members")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(memberDtoJson))
                .andReturn();
    }

    @Test
    public void 회원등록_userName_길이부족() throws Exception {

        MemberDto memberDto = getShortUserNameMemberDto();

        String memberDtoJson = objectMapper.writeValueAsString(memberDto);

        MvcResult result = mockMvc
                .perform(
                        post("/api/v1/members")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(memberDtoJson))
                .andExpect(
//                        (rslt) -> assertTrue(rslt.getResolvedException().getClass().isAssignableFrom(BindException.class))
//                        (rslt) -> assertEquals(
//                                rslt.getResolvedException().getClass().getCanonicalName(),
//                                BindException.class.getCanonicalName()
//                        )
                        (rslt) -> assertThat(rslt.getResolvedException().getClass().getCanonicalName())
                                .isEqualTo(BindException.class.getCanonicalName())
                )
                .andReturn();
    }

    @Test
    public void 회원조회_email() throws Exception {

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

        MvcResult result1 = mockMvc
                .perform(get("/api/v1/members?email=" + email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(userName)))
                .andExpect(jsonPath("$.email", is(email)))
                .andReturn();
    }

    @Test
    public void 회원조회_userName() throws Exception {

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

        MvcResult result1 = mockMvc
                .perform(get("/api/v1/members?userName=" + userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(userName)))
                .andExpect(jsonPath("$.email", is(email)))
                .andReturn();
    }

    @Test(expected = NestedServletException.class)
    public void 회원조회_noArg() throws Exception {

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

        MvcResult result1 = mockMvc
                .perform(get("/api/v1/members"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName", is(userName)))
                .andExpect(jsonPath("$.email", is(email)))
                .andReturn();
    }

    private MemberDto getShortUserNameMemberDto() {
        String userName = "1234567";
        String email = "homo.efficio@gmail.com";
        MemberDto memberDto = new MemberDto();
        memberDto.setUserName(userName);
        memberDto.setEmail(email);
        return memberDto;
    }
}
