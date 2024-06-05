<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.javarush.dyuzhevquest.entity.Question" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%= ((Question) request.getAttribute("question")).getAnswers() %>--%>
<%--<%= request.getAttribute("question") %>--%>

<!DOCTYPE html>
<html>
<head>
    <title>Game</title>
</head>
<body>
<h1>${question.questionText}</h1>
<form method="post">
    <input type="hidden" name="id" value="${question.id}">
    <c:forEach var="answer" items="${question.answers}">
        <form action="/game" method="post">
            <button type="submit" name="answerId" value="${answer.id}">${answer.answerText}</button>
        </form>
    </c:forEach>
</form>
<p>You have played ${sessionScope.gamesPlayed} games.</p>
</body>
</html>