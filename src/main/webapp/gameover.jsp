<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <link href="style.css" rel="stylesheet">
    <title>Game Over</title>
</head>
<body class="${sessionScope.bodyClass}">
<div class="container">

    <br><br>
    <h1>Game Over</h1><br>

    <p class="game-over-message">${sessionScope.gameOverMessage}</p><br>
    <div style="display: flex; justify-content: center;">
        <a href="./game" class="btn btn-secondary btn-custom1">НАЧАТЬ ЗАНОВО</a>
        <br><br>
    </div>
    <br><p><h5>Статистика:</h5></p>
    <p class="games-played-text">Имя: ${sessionScope.playerName}</p>
    <p class="games-played-text">Игр сыграно: ${sessionScope.gamesPlayed}</p>

</div>
</body>
</html>