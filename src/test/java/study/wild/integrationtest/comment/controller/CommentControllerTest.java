package study.wild.integrationtest.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import study.wild.comment.domain.CommentCreate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:test-application.properties")
@SqlGroup({
        @Sql(value = "/sql/comment/comment-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("사용자는 새로운 댓글을 생성할 수 있다")
    void 사용자는_새로운_댓글을_생성할_수_있다() throws Exception {
        //given
        CommentCreate commentCreate = CommentCreate.builder()
                .content("createTest")
                .postId(1L)
                .build();
        //when
        //then
        mockMvc.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("3"))
                .andExpect(jsonPath("$.content").value("createTest"));
    }

    @Test
    @DisplayName("사용자는 새로운 댓글을 생성할 때 내용을 입력하지 않으면 생성할 수 없다")
    void 사용자는_새로운_댓글을_생성할_때_내용을_입력하지_않으면_생성할_수_없다() throws Exception {
        //given
        CommentCreate commentCreate = CommentCreate.builder()
                .content("")
                .postId(1L)
                .build();
        //when
        //then
        mockMvc.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentCreate)))
                .andExpect(status().isBadRequest());
    }
}
