package com.sparta.managemyschedule.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.managemyschedule.auth.security.UserDetailsImpl;
import com.sparta.managemyschedule.common.Data;
import com.sparta.managemyschedule.controller.ScheduleController;
import com.sparta.managemyschedule.dto.responseDto.CreateResponseDto;
import com.sparta.managemyschedule.entity.Schedule;
import com.sparta.managemyschedule.entity.User;
import com.sparta.managemyschedule.service.ScheduleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {ScheduleController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
@MockBean(JpaMetamodelMappingContext.class)
@ActiveProfiles("test")
public class ScheduleControllerTest {
    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    private Principal mockPrincipal;

    @MockBean
    ScheduleService scheduleService;

    @MockBean
    User testUser;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();

    }

    private void mockUserSetup() throws Exception {
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails,"",testUserDetails.getAuthorities());
    }

    @Test
    @DisplayName("일정 생성 성공")
    void 일정생성_성공() throws Exception{
        mockUserSetup();
        Schedule schedule = Data.getCreateSuccessSchedule();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/schedules")
                        .principal(mockPrincipal)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule))
                    )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("일정 전체 조회 성공")
    void 일정전체조회_성공() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedules")
                        .principal(mockPrincipal)
                        .param("page",String.valueOf(1))
                        .param("size",String.valueOf(20))
                        .param("sortBy","id")
                        .param("isAsc",String.valueOf(true))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 한 사용자 일정 상세 조회")
    void 상세조회_성공1() throws Exception{
        mockUserSetup();
        CreateResponseDto createResponseDto = Data.getDto();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedules/{scheduleId}",createResponseDto.getScheduleId())
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("로그인 안 한 사용자 일정 상세 조회 성공")
    void 상세조회_성공2() throws Exception{
        CreateResponseDto createResponseDto = Data.getDto();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedules/{scheduleId}",createResponseDto.getScheduleId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("없는 글 상세 조회 실패")
    void 상세조회_실패1() throws Exception{
        CreateResponseDto createResponseDto = Data.getDto();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedules/{scheduleId}",(double)createResponseDto.getScheduleId()/37))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
                })
                .andDo(print());
    }
}