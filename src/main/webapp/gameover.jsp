<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Game Over</title>
</head>
<body>
<h1>Game Over</h1>
<p>Вы играли ${sessionScope.gamesPlayed} раз.</p>
<p>Имя: ${sessionScope.playerName}</p>
<p>${sessionScope.gameOverMessage}</p>
<a href="./game">Play Again</a>
</body>
</html>