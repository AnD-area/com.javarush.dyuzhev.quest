package com.javarush.dyuzhevquest.service;

import com.javarush.dyuzhevquest.entity.Answer;
import com.javarush.dyuzhevquest.entity.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        gameService = new GameService();
    }

    @Test
    void getQuestionById() {
        Question question = gameService.getQuestionById("1");
        assertNotNull(question);
        assertEquals("1", Long.toString(question.getId()));
    }

    @Test
    void getAnswerById() {
        Answer answer = gameService.getAnswerById("11");
        assertNotNull(answer);
        assertEquals("11", answer.getId());
    }
}