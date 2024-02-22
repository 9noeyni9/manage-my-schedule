package com.sparta.managemyschedule.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.managemyschedule.auth.security.UserDetailsImpl;
import com.sparta.managemyschedule.controller.UserController;
import com.sparta.managemyschedule.dto.requestDto.SignupRequestDto;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {UserController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    private Principal mockPrincipal;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
//        User user = new User("testDb123", "testDb123", "testDb123@test.com");
    }

    private void mockUserSetup(){
        String username = "test0000";
        String password = "test0000";
        String email = "test0000@test.com";
        User testUser = new User(username,password,email);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails,"",testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("회원가입성공")
    void 회원가입_성공() throws Exception{
        // given
        SignupRequestDto signupRequestDto = new SignupRequestDto("test1111","test1111","test1111@test.com");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupRequestDto))
                )
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("회원조회성공")
    void 회원조회_성공() throws Exception{
        // given
        mockUserSetup();

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user-info")
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}