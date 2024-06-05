package com.javarush.dyuzhevquest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher dispatcher;

    private GameServlet servlet;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
    }

    @Test
    public void DoGet() throws ServletException, IOException {
        when(request.getParameter("questionId")).thenReturn("1");
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher("/question.jsp");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void DoPost() throws ServletException, IOException {
        when(request.getParameter("playerName")).thenReturn("TestPlayer");
        servlet.doPost(request, response);
        verify(session).setAttribute("playerName", "TestPlayer");
        verify(response).sendRedirect("/game?id=1");
    }
}