<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page import="com.javarush.dyuzhevquest.entity.Question" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>
<%--<%= ((Question) request.getAttribute("question")).getAnswers() %>--%>
<%--<%= request.getAttribute("question") %>--%>


<!DOCTYPE html>
<html>
<head>
    <link href="style.css" rel="stylesheet">
    <title>Game</title>

</head>
<body>
<br><br><h1>${question.questionText}</h1> <br><br>
<c:forEach var="answer" items="${question.answers}">
    <form action="/game" method="post" class="mb-3">
        <input type="hidden" name="id" value="${question.id}">
        <button class="btn btn-secondary" type="submit" name="answerId" value="${answer.id}">${answer.answerText}</button>
    </form>
</c:forEach>
<p>Игр сыграно: ${sessionScope.gamesPlayed} раз.</p>

</body>
</html>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link href="style.css" rel="stylesheet">--%>
<%--    <title>Game</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>${question.questionText}</h1>--%>
<%--<form method="post">--%>
<%--    <input type="hidden" name="id" value="${question.id}">--%>
<%--    <c:forEach var="answer" items="${question.answers}">--%>
<%--        <form action="/game" method="post">--%>
<%--            <button class="answer-button" type="submit" name="answerId" value="${answer.id}">${answer.answerText}</button>--%>
<%--        </form>--%>
<%--    </c:forEach>--%>
<%--</form>--%>
<%--<p>Игр сыграно: ${sessionScope.gamesPlayed} раз.</p>--%>

<%--</body>--%>
<%--</html>--%>