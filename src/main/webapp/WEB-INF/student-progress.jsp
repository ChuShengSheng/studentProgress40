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
    <title>student-progress</title>
    <script>
        $(function () {
            $("#date").datepicker();
        });
    </script>
</head>
<body>
<aside class="navigation">
    <a href="/" class="navigation-item">На главную</a>
    <a href="/students" class="navigation-item">Назад</a>

</aside>

<main class="main">
    <h1 class="main-heading"> Система управления студентами и их успеваемостью</h1>
    <h2 class="heading-secondary">Отображена успеваемость для следующего студента:</h2>

    <div class="container">
        <table class="student-table" border="1">
            <tr>
                <th> Фамилия</th>
                <th> Имя</th>
                <th> Группа</th>
                <th> Дата поступления</th>
            </tr>
            <tr>
                <td>${student.surname}</td>
                <td>${student.name}</td>
                <td>${student.group}</td>
                <td><fmt:formatDate value="${student.date}" pattern="dd/MM/yyyy"/></td>
            </tr>
        </table>


    </div>

    <section class="semestr-note">
        <div>
            <table class="disciplina-note" border="1">
                <tr>
                    <th>Дисциплина</th>
                    <th>Оценка</th>
                </tr>
                <c:forEach items="${marks}" var="mark">
                    <tr>
                        <td>${mark.discipline.discipline}</td>
                        <td>${mark.mark}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
        <div class="choice-semestr">
            <h2> Выбрать семестр : </h2>
            <form method="get" action="/student-progress">
                <input type="hidden" name="studentProgressHidden" value="${student.id}">
                <select border="1" name="idSelectedTerm">
                    <c:forEach items="${terms}" var="term">

                        <c:choose>
                            <c:when test="${term.id eq selectedTerm.id}">
                                <option selected value="${term.id}" class="choice">${term.term}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${term.id}" class="choice">${term.term}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>

                </select>

                <input type="submit" class="choice-btn" value="Выбрать">
            </form>
        </div>
        <h5><fmt:formatNumber type="number" value="${average}" maxFractionDigits="2"></fmt:formatNumber></h5>
    </section>


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
