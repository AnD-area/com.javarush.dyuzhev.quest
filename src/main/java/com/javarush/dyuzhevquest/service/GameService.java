package com.javarush.dyuzhevquest.service;

import com.javarush.dyuzhevquest.entity.Answer;
import com.javarush.dyuzhevquest.entity.Question;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.*;

@Getter
public class GameService {
    private final List<Question> questions;

    public GameService() {
        questions = new ArrayList<>();
        loadQuestionsAndAnswers();
    }

    private void loadQuestionsAndAnswers() {
        Map<String, Answer> answerMap = new HashMap<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("quest_q_and_a.txt");
        if (is == null) {
            throw new RuntimeException("Resource quest_q_and_a.txt not found");
        }
        List<String> lines = IOUtils.readLines(is, "UTF-8");
        for (String line : lines) {
            String[] parts = line.split(": ", 2);
            if (parts[0].length() == 1) {
                Question question = new Question();
                question.setId(Long.parseLong(parts[0]));
                question.setQuestionText(parts[1]);
                question.setAnswers(new ArrayList<>());
                questions.add(question);
            } else if (parts[0].length() == 2) {
                Answer answer = new Answer();
                answer.setId(parts[0]);
                answer.setAnswerText(parts[1]);
                answerMap.put(answer.getId(), answer);
            }
        }
        // Linking answers to questions
        for (Question question : questions) {
            String questionId = Long.toString(question.getId());
            for (int i = 1; i <= 2; i++) {
                Answer answer = answerMap.get(questionId + i);
                if (answer != null) {
                    question.getAnswers().add(answer);
                }
            }
        }
        // Linking answer to nextQuestion
        for (int i = 0; i < questions.size() - 1; i++) {
            Question question = questions.get(i);
            for (Answer answer : question.getAnswers()) {
                if (answer.getId().endsWith("1")) {
                    answer.setNextQuestion(questions.get(i + 1));
                    answer.setAnswerCorrect(true);
                } else {
                    answer.setAnswerCorrect(false);
                }
            }
        }
        // Processing the last question separately
        Question lastQuestion = questions.get(questions.size() - 1);
        for (Answer answer : lastQuestion.getAnswers()) {
            if (answer.getId().equals("31")) {
                answer.setAnswerCorrect(true);
            } else if (answer.getId().equals("32")) {
                answer.setAnswerCorrect(false);
            }
        }
    }

    public Question getQuestionById(String questionId) {
        for (Question q : questions) {
            if (Long.toString(q.getId()).equals(questionId)) {
                return q;
            }
        }
        return null;
    }

    public Answer getAnswerById(String answerId) {
        for (Question question : questions) {
            for (Answer answer : question.getAnswers()) {
                if (answer.getId().equals(answerId)) {
                    return answer;
                }
            }
        }
        return null;
    }

    public Question handleGetRequest(String questionId, HttpSession session) {
        if (questionId == null) {
            questionId = "1"; // Start 1 question if no ID provided
        }

        Question question = getQuestionById(questionId);

        // If this is the first question, set the body class to answer-waiting for accessing wallpaper
        if ("1".equals(questionId)) {
            session.setAttribute("bodyClass", "answer-waiting");
        }

        return question;
    }

    public void handlePostRequest(String playerName, String answerId, HttpSession session, HttpServletRequest request) {
        if (playerName != null) {
            session.setAttribute("playerName", playerName);

            if (session.getAttribute("gamesPlayed") == null) {
                session.setAttribute("gamesPlayed", 0);
            }
            // Set isAnswerCorrect to false to start a new game
            session.setAttribute("isAnswerCorrect", false);
            request.setAttribute("redirect", "/game?id=1");
            return;
        }

        Answer selectedAnswer = getAnswerById(answerId);

        if (selectedAnswer != null) {
            session.setAttribute("isAnswerCorrect", selectedAnswer.isAnswerCorrect());
            String className = selectedAnswer.isAnswerCorrect() ? "answer-right" : "answer-wrong";
            session.setAttribute("bodyClass", className);
            if (selectedAnswer.getNextQuestion() != null) {
                // If nextQuestion provided, set body class to answer-waiting and redirect to it
                session.setAttribute("bodyClass", "answer-waiting");
                request.setAttribute("redirect", "/game?questionId=" + selectedAnswer.getNextQuestion().getId());
            } else {
                handleGameOver(selectedAnswer, session, request);
            }
        }
    }

    private void handleGameOver(Answer selectedAnswer, HttpSession session, HttpServletRequest request) {
        // If there's no nextQuestion, it's gameOver
        String gameOverMessage;
        if (selectedAnswer.getId() != null) {
            Map<String, String> gameOverMessages = new HashMap<>();
            gameOverMessages.put("31", "Вперед - в светлое будущее!");
            gameOverMessages.put("12", "Да вы шутник! Давайте заново");
            gameOverMessages.put("22", "Вы утонули в утиной типизации!");
            gameOverMessages.put("32", "+5 к индусскому английскому, -10 к мотивации");

            gameOverMessage = gameOverMessages.getOrDefault(selectedAnswer.getId(), "");
        } else {
            gameOverMessage = "Игра окончена";
        }
        session.setAttribute("gameOverMessage", gameOverMessage);

        Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
        if (gamesPlayed == null) {
            gamesPlayed = 0;
        }
        gamesPlayed++;
        session.setAttribute("gamesPlayed", gamesPlayed);
        request.setAttribute("isAnswerCorrect", selectedAnswer.isAnswerCorrect());
        request.setAttribute("redirect", "/gameover.jsp");
    }
}