package com.javarush.dyuzhevquest.service;

import com.javarush.dyuzhevquest.entity.Answer;
import com.javarush.dyuzhevquest.entity.Question;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class GameService {
//    public String getGameDescription(String filePath) {
//        StringBuilder description = new StringBuilder();
//
//        try {
//            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                description.append(line).append("\n");
//            }
//            reader.close();
//        } catch (Exception e) {
//            return "Ошибка при чтении файла: " + e.getMessage();
//        }
//        return description.toString();
//    }

//    public List<Question> loadQuestions() throws IOException {
//        List<Question> questions = new ArrayList<>();
//        Map<String, Answer> answerMap = new HashMap<>();
//        InputStream is = getClass().getClassLoader().getResourceAsStream("quest_q_and_a.txt");
//        List<String> lines = IOUtils.readLines(is, "UTF-8");
//        for (String line : lines) {
//            String[] parts = line.split(": ", 2);
//            if (parts[0].length() == 1) {
//                Question question = new Question();
//                question.setId(Long.parseLong(parts[0]));
//                question.setQuestionText(parts[1]);
//                question.setAnswers(new ArrayList<>());
//                questions.add(question);
//            } else if (parts[0].length() == 2) {
//                Answer answer = new Answer();
//                answer.setId(parts[0]);
//                answer.setAnswerText(parts[1]);
//                answerMap.put(answer.getId(), answer);
//            }
//        }
//
//        // Link answers to questions
//        for (Question question : questions) {
//            String questionId = Long.toString(question.getId());
//            for (int i = 1; i <= 2; i++) {
//                Answer answer = answerMap.get(questionId + i);
//                if (answer != null) {
//                    question.getAnswers().add(answer);
//                }
//            }
//        }
//
//        // Link answer to the next question
//        for (int i = 0; i < questions.size() - 1; i++) {
//            Question question = questions.get(i);
//            for (Answer answer : question.getAnswers()) {
//                if (answer.getId().endsWith("1")) {
//                    answer.setNextQuestion(questions.get(i + 1));
//                    answer.setAnswerCorrect(true);
//                } else {
//                    answer.setAnswerCorrect(false);
//                }
//            }
//        }
//
//        // Handle the last question separately
//        Question lastQuestion = questions.get(questions.size() - 1);
//        for (Answer answer : lastQuestion.getAnswers()) {
//            if (answer.getId().equals("31")) {
//                answer.setAnswerCorrect(true);
//            } else if (answer.getId().equals("32")) {
//                answer.setAnswerCorrect(false);
//            }
//        }
//        return questions;
//    }
//
//    public Question findQuestionById(List<Question> questions, String id) {
//        for (Question q : questions) {
//            if (Long.toString(q.getId()).equals(id)) {
//                return q;
//            }
//        }
//        return null;
//    }
//
//    public Answer processAnswer(List<Question> questions, String answerId) {
//        for (Question question : questions) {
//            for (Answer answer : question.getAnswers()) {
//                if (answer.getId().equals(answerId)) {
//                    return answer;
//                }
//            }
//        }
//        return null;
//    }
//}