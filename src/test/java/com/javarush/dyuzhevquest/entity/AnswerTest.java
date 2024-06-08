package com.javarush.dyuzhevquest.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {

    @Test
    public void testAnswer() {
        Answer answer = new Answer();
        answer.setText("Test");
        answer.setId("1");
        answer.setAnswerText("Test Answer");
        answer.setNextQuestion(null);
        answer.setGameOverMessage("Game Over");
        answer.setAnswerCorrect(true);

        assertEquals("Test", answer.getText());
        assertEquals("1", answer.getId());
        assertEquals("Test Answer", answer.getAnswerText());
        assertEquals(null, answer.getNextQuestion());
        assertEquals("Game Over", answer.getGameOverMessage());
        assertTrue(answer.isAnswerCorrect());
    }
}