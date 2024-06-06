package com.javarush.dyuzhevquest.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testQuestion() {
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        List<Answer> answers = Arrays.asList(answer1, answer2);

        Question question = new Question();
        question.setId(1L);
        question.setQuestionText("Test Question");
        question.setAnswers(answers);

        assertEquals(1L, question.getId());
        assertEquals("Test Question", question.getQuestionText());
        assertEquals(2, question.getAnswers().size());
    }
}