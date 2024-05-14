<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kelly+Slab&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <title>login</title>
    <script>
        $(function () {
            $("#date").datepicker();
        });
    </script>
</head>
<body>


<main class="main">
    <h1 class="main-heading-title"> Система управления студентами и их успеваемостью
    </h1>
    <div class="beginning">
        <h2>
            Введите логин, роль и пароль
        </h2>
        <br>
        <form action="/login" method="post">
            <p>Логин: </p>
            <input name="login" type="text">
            <br>
            <br>
            <p>Пароль: </p>

            <input name="password" type="text">
            <br>
            <br>
            <p>Роль :</p>
            <select name="role">
                <option value="1" style="font-family: Sylfaen; font-size: 10pt;"> Администратор</option>
                <option value="2" style="font-family: Sylfaen; font-size: 10pt;"> Студент</option>

            </select>
            <br>
            <br>
            <br>
            <input type="submit" value=" Войти" class="other">

            <c:if test="${message eq 1}">
                <h3>Поля не должны быть пустыми!!!</h3>
            </c:if>
            <c:if test="${message eq 2}">
                <h3>Неверный логин или пароль!!! Или у вас не та роль!!!</h3>
            </c:if>
        </form>

        <br>

        <form>
            <div align="right">
                <h3>Тестовый аккаунт администратора:</h3>

                    <h4>Логин: admin</h4>
                    <h4>Пароль: 123</h4>
                <br>
                <h3>Тестовый аккаунт студента:</h3>

                    <h4>Логин: student</h4>
                    <h4>Пароль: 123</h4>

            </div>
        </form>
    </div>

</main>

</body>
</html>
