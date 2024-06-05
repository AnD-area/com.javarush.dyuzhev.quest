<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="style.css" rel="stylesheet">
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">--%>
    <title>Game Over</title>
</head>
<body>
<h1>Game Over</h1>

<p>${sessionScope.gameOverMessage}</p>
<p><a href="./game">НАЧАТЬ ЗАНОВО</a></p>
<p><h4>Статистика:</h4></p>
<p>Имя: ${sessionScope.playerName}</p>
<p>Игр сыграно: ${sessionScope.gamesPlayed} раз</p>

<%--<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"--%>
<%--        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"--%>
<%--        crossorigin="anonymous"></script>--%>
</body>
</html>