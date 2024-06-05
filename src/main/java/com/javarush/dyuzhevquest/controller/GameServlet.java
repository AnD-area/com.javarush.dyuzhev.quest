package com.javarush.dyuzhevquest.controller;

import com.javarush.dyuzhevquest.entity.Answer;
import com.javarush.dyuzhevquest.entity.Question;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private List<Question> questions;

    @Override
    public void init() throws ServletException {
        super.init();
        questions = new ArrayList<>();
        Map<String, Answer> answerMap = new HashMap<>();
        InputStream is = getClass().getClassLoader().getResourceAsStream("quest_q_and_a.txt");
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

        // Связываем ответы с вопросами
        for (Question question : questions) {
            String questionId = Long.toString(question.getId());
            for (int i = 1; i <= 2; i++) {
                Answer answer = answerMap.get(questionId + i);
                if (answer != null) {
                    question.getAnswers().add(answer);
                }
            }
        }

        // Связываем ответ со следующим вопросом
        for (int i = 0; i < questions.size() - 1; i++) {
            Question question = questions.get(i);
            for (Answer answer : question.getAnswers()) {
                if (answer.getId().endsWith("1")) {
                    answer.setNextQuestion(questions.get(i + 1));
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String questionId = request.getParameter("questionId");
        if (questionId == null) {
            questionId = "1"; // Начинаем с первого вопроса, если нет его ID
        }

        // Находим вопрос по ID
        Question question = null;
        for (Question q : questions) {
            if (Long.toString(q.getId()).equals(questionId)) {
                question = q;
                break;
            }
        }

        if (question == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Question not found");
            return;
        }

        request.setAttribute("question", question);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/question.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String playerName = request.getParameter("playerName");
        if (playerName != null) {
            // Если пользователь ввел имя, сохранить его в сессии и перенаправить на первый вопрос
            request.getSession().setAttribute("playerName", playerName);
            response.sendRedirect("/game?id=1");
            return;
        }
        String answerId = request.getParameter("answerId");
        Answer selectedAnswer = null;
        for (Question question : questions) {
            for (Answer answer : question.getAnswers()) {
                if (answer.getId().equals(answerId)) {
                    selectedAnswer = answer;
                    break;
                }
            }
        }

        if (selectedAnswer != null) {
            if (selectedAnswer.getNextQuestion() != null) {
                // Если есть следующий вопрос, перенаправляем на него
                response.sendRedirect("/game?questionId=" + selectedAnswer.getNextQuestion().getId());
            } else {
                // Если нет следующего вопроса, игра закончена
                String gameOverMessage;
                if (selectedAnswer.getId() != null) {
                    Map<String, String> gameOverMessages = new HashMap<>();
                    gameOverMessages.put("31", "Вперед - в светлое будущее!");
                    gameOverMessages.put("12", "Да вы шутник! Давайте заново");
                    gameOverMessages.put("22", "Вы утонули в утиной типизации!");
                    gameOverMessages.put("32", "+5 к индусскому английскому, -10 к мотивации");

                    gameOverMessage = gameOverMessages.getOrDefault(selectedAnswer.getId(), "");
                } else {
                    // когда selectedAnswer.getId() == null
                    gameOverMessage = "Игра окончена";
                }
                request.getSession().setAttribute("gameOverMessage", gameOverMessage);

                Integer gamesPlayed = (Integer) request.getSession().getAttribute("gamesPlayed");
                if (gamesPlayed == null) {
                    gamesPlayed = 0;
                }
                gamesPlayed++;
                request.getSession().setAttribute("gamesPlayed", gamesPlayed);
                request.getRequestDispatcher("/gameover.jsp").forward(request, response);
            }
        }
    }
}