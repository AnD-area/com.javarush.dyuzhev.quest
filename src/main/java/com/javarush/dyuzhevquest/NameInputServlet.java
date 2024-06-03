package com.javarush.dyuzhevquest;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/processName")
public class NameInputServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("playerName");
        HttpSession session = request.getSession();
        session.setAttribute("playerName", playerName);
        response.sendRedirect("nextPage"); // Перенаправьте пользователя на следующую страницу
    }
}