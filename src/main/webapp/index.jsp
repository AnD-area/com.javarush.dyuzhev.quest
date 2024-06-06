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
<div class="container">
    <br><h2>Вступление:</h2><br>

    <div class="intro-text text-left">
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
    </div>

    <form action="game" method="post">
        <label for="playerName" class="label-text">Введите ваше имя:</label>
        <br><br><div class="input-group mb-3 justify-content-center gap-2">
            <div class="col-4">
                <input type="text" class="form-control input-field" id="playerName" name="playerName">
            </div>
            <div class="input-group-append">
                <button type="submit" class="btn btn-secondary">Ввод</button>
            </div>
        </div>
    </form>

<%--    <h5><p>Введите ваше имя:</p></h5>--%>
<%--    <form action="game" method="post">--%>
<%--        <input type="text" name="playerName">--%>
<%--        <input type="submit" value="Enter">--%>
<%--    </form>--%>

</div>
</body>
</html>