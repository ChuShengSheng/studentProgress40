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
    <script type="application/javascript" src="../resources/js/functions.js"></script>
    <title>student</title>
</head>
<body>
<aside class="navigation">
    <a href="/" class="navigation-item">На главную</a>

</aside>

<main class="main">
    <h1 class="main-heading"> Система управления студентами и их успеваемостью
    </h1>

    <div class="student-btns">
        <div class="student-btns1">
            <input type="submit" onclick="studentProgress()" class="student-btn"
                   value="Посмотреть успеваемость выбранного студента"/>
            <c:if test="${role eq 1}"><input type="submit" onclick="modifyStudents()" class="student-btn"
                                             value="Модифицировать выбранного студента"/></c:if>
        </div>
        <c:if test="${role eq 1}">
            <div class="student-btns1">
                <form action="/student-create" method="get">
                    <input type="submit" class="student-btn" value="Создать студента"/>
                </form>
                <input type="submit" onclick="deleteStudents()" class="student-btn"
                       value="Удалить выбранных студентов"/>
            </div>
        </c:if>
    </div>
    <h2 class="heading-secondary">
        Список студентов
    </h2>
    <table class="student-table" border="1">
        <tr>
            <th>&nbsp</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        <c:forEach items="${students}" var="st">
            <tr>

                <td><input type="checkbox" name="idStudent" value="${st.id}">
                </td>

                <td>${st.surname}</td>
                <td>${st.name}</td>
                <td>${st.group}</td>
                <td><fmt:formatDate value="${st.date}" pattern="dd/MM/yyyy"/>
                </td>
            </tr>
        </c:forEach>
    </table>

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

<form action="/student-delete" method="post" id="deleteStudentForm">
    <input type="hidden" value="" id="deleteStudentHidden" name="idsToDelete">
</form>
<form action="/student-modify" method="get" id="modifyStudentForm">
    <input type="hidden" value="" id="modifyStudentHidden" name="idsToModify">
</form>
<form action="/student-progress" method="get" id="studentProgressForm">
    <input type="hidden" value="" id="studentProgressHidden" name="studentProgressHidden">
</form>

</body>
</html>