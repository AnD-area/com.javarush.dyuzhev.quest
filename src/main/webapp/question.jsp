<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.javarush.dyuzhevquest.entity.Question" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>
<%--<%= ((Question) request.getAttribute("question")).getAnswers() %>--%>
<%--<%= request.getAttribute("question") %>--%>

<!DOCTYPE html>
<html>
<head>
    <link href="style.css" rel="stylesheet">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">--%>
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
<p>Игр сыграно: ${sessionScope.gamesPlayed} раз.</p>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"--%>
<%--        crossorigin="anonymous"></script>--%>
</body>
</html>