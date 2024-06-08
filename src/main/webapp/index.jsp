<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/bootstrap_template.jsp" %>
<%@ page import="org.apache.commons.io.IOUtils" %>
<%@ page import="java.util.Objects" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet">
    <title>Welcome</title>
    <script>
        function setWaitingBackground() {
            document.body.className = 'answer-waiting';
        }
    </script>
</head>
<body class="initial">
<div class="container">
    <br>
    <h2>Вступление:</h2><br>

    <div class="intro-text text-left">
        <% String filePath = "/quest_description.txt";
            try {
                String content = IOUtils.toString(Objects.requireNonNull(getClass().getClassLoader()
                        .getResourceAsStream(filePath)), StandardCharsets.UTF_8);
                out.println(content);
            } catch (Exception e) {
                out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        %>

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