package com.QuizMaker.QuizMakerApp.unittests.controllertests;

import com.QuizMaker.QuizMakerApp.controllers.QuizController;
import com.QuizMaker.QuizMakerApp.models.Quiz;
import com.QuizMaker.QuizMakerApp.repositories.jpa.QuizRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(QuizController.class)
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizRepository quizRepository;

    @MockBean
    private PlatformTransactionManager transactionManager;

    private final String requestBasePath = "/quiz";

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void updateTestShouldReturn200AndQuizWhenQuizIsValid() throws Exception {
        // arrange
        UUID id = UUID.randomUUID();
        Long entityId = 1L;
        Quiz quiz = createQuiz(id, "UpdatedQuiz");
        quiz.setEntityId(entityId);
        String requestPath = requestBasePath + "/" + id;
        when(quizRepository.getEntityIdById(id)).thenReturn(Optional.of(entityId));
        when(quizRepository.save(quiz)).thenReturn(quiz);
        String serialized = mapToJson(quiz);

        // act and assert
        mockMvc.perform(put(requestPath).with(csrf())
                        .content(serialized).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(serialized));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void updateTestShouldReturn404WhenQuizIsNotInDatabase() throws Exception {
        // arrange
        UUID searchedId = UUID.randomUUID();
        UUID quizId = UUID.randomUUID();
        Quiz quiz = createQuiz(quizId, "UpdatedQuiz");
        String requestPath = requestBasePath + "/" + searchedId;
        when(quizRepository.getEntityIdById(searchedId)).thenReturn(Optional.empty());
        String serialized = mapToJson(quiz);

        // act and assert
        mockMvc.perform(put(requestPath).with(csrf())
                        .content(serialized).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void getTestShouldReturn200AndQuizWhenQuizIsInDatabase() throws Exception {
        // arrange
        UUID id = UUID.randomUUID();
        Quiz quiz = createQuiz(id, "Test1");
        String requestPath = requestBasePath + "/" + id;
        when(quizRepository.getQuizById(id)).thenReturn(Optional.of(quiz));

        // act and assert
        mockMvc.perform(get(requestPath))
                .andExpect(status().is(200));

    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private Quiz createQuiz(UUID id, String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setId(id);
        return quiz;
    }
}
