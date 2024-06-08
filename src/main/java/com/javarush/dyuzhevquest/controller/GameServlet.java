package com.javarush.dyuzhevquest.controller;

import com.javarush.dyuzhevquest.entity.Question;
import com.javarush.dyuzhevquest.service.GameService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;


@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String questionId = request.getParameter("questionId");

        Question question = gameService.handleGetRequest(questionId, request.getSession());

        if (question == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Question not found");
            return;
        }

        request.setAttribute("question", question);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/question.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String playerName = request.getParameter("playerName");
        String answerId = request.getParameter("answerId");

        gameService.handlePostRequest(playerName, answerId, request.getSession(), request);

        String redirect = (String) request.getAttribute("redirect");
        if (redirect != null) {
            response.sendRedirect(redirect);
        } else {
            request.getRequestDispatcher("/gameover.jsp").forward(request, response);
        }
    }
}