<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <link href="style.css" rel="stylesheet">
    <title>Game Over</title>
</head>
<body>
<h1>Game Over</h1>

<p>${sessionScope.gameOverMessage}</p>
<p><a href="./game">НАЧАТЬ ЗАНОВО</a></p>
<p><h4>Статистика:</h4></p>
<p>Имя: ${sessionScope.playerName}</p>
<p>Игр сыграно: ${sessionScope.gamesPlayed} раз</p>

</body>
</html>