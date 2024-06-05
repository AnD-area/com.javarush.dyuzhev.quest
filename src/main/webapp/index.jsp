<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.FileReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/bootstrap_template.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet">
    <title>Welcome</title>
</head>
<body>

<p><h2>Вступление:</h2></p>

<% String filePath = request.getServletContext().getRealPath("/quest_description.txt");
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            out.println(line);
        }
        reader.close();
    } catch (Exception e) {
        out.println("Ошибка при чтении файла: " + e.getMessage());
    }
%>

<h5><p>Введите ваше имя:</p></h5>
<form action="game" method="post">
    <input type="text" name="playerName">
    <input type="submit" value="Enter">
</form>

</body>
</html>