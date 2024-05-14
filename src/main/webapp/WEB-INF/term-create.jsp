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
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Kelly+Slab&display=swap" rel="stylesheet">
    <title>term-creating</title>
</head>
<body>
<aside class="navigation">
    <a href="/" class="navigation-item">На главную</a>
    <a href="/terms" class="navigation-item">Назад</a>

</aside>

<main class="main">
    <h1 class="main-heading"> Система управления студентами и их успеваемостью</h1>
    <h2 class="heading-secondary">Для создания семестра заполните следующие данные и нажмите кнопку "Создать":</h2>
    <br>
    <form action="/term-create" method="post">
        <div class="name-window">
            <label for="duration"><h3>Длительность (в неделях)</h3></label>
            <input name="duration" type="text" id="duration">
        </div>
        <br>

        <br>

        <div class="discip-in-sem">
            <h3> Дисциплины в семестре :</h3>

            <select multiple class="select-discip" >

                <c:forEach items="${disciplines}" var="disciplines">
                    <option>${disciplines.discipline}</option>
                </c:forEach>

            </select>
            <table>
                <tr>
                    <td></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td></td>
                </tr>
            </table>
        </div>
        <br>

        <input type="submit" value=" Создать" class="field">
        <c:if test="${message eq 1}">
            <h3>Поля не должны быть пустыми!!!</h3>
        </c:if>
    </form>
</main>
<aside class="logout">
    <c:choose>
        <c:when test="${isLogin eq 1}">
            <a href="/logout" class="logout-btn">Logout, ${login}</a>
        </c:when>
        <c:otherwise>
            <a href="/login" class="logout-btn">Login</a>
        </c:otherwise>
    </c:choose>

</aside>

</body>
</html>
