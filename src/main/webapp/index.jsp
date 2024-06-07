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
<body class="initial">
<div class="container">
    <br>
    <h2>Вступление:</h2><br>

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

        <script>
            function setWaitingBackground() {
                document.body.className = 'answer-waiting';
            }
        </script>

        <div class="d-flex justify-content-center">
            <form action="game" method="post" onsubmit="setWaitingBackground()">
                <div class="d-flex justify-content-center">
                    <label for="playerName" class="label-text">Введите ваше имя:</label>
                </div>
                <br>
                <div class="input-group mb-3 justify-content-center gap-2">
                    <div class="col-7">
                        <input type="text" class="form-control input-field" id="playerName" name="playerName">
                    </div>
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary">Ввод</button>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>