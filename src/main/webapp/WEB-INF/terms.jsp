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
    <title>terms</title>
</head>
<body>
<aside class="navigation">
    <a href="/" class="navigation-item">На главную</a>

</aside>

 <main class="main">
    <h1 class="main-heading"> Система управления студентами и их успеваемостью</h1>
    <div class="choice-semestr1">
        <h2> Выбрать семестр : </h2>
        <form method="get" action="/terms">
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
    <br>


     <label name="selectedDuration"><h2>Длительность семестра:</h2> ${selectedTerm.duration}</label>

    <br>
    <h2>
        Список дисциплин семестра
    </h2>
    <br>
    <div class="term">
        <table border="1">
            <tr>
                <th> Наименование дисциплины</th>
            </tr>

            <c:forEach items="${allDisciplineByTerm}" var="discipline">
                <tr>
                    <td>${discipline.discipline}</td>
                </tr>
            </c:forEach>
        </table>


        <div class="discipline-btns">
            <form action="/term-create" method="get">
                <c:if test="${role eq 1}">
                  <input type="submit" class="student-btn" value="Создать семестр"/>
                </c:if>
            </form>

            <form action="/term-modify" method="get">
                <input type="hidden" name="idsToModify" id="modifyTermHidden" value="${selectedTerm.id}">
                <div>
                    <c:if test="${role eq 1}">
                      <input type="submit" class="student-btn" value="Модифицировать текущий семестр"/>
                    </c:if>
                </div>
            </form>

            <form action="/term-delete" method="post">
                <input type="hidden" name="idsToDelete" id="deleteTermHidden" value="${selectedTerm.id}">
                <div>
                  <c:if test="${role eq 1}">
                    <input type="submit" class="student-btn" value="Удалить текущий семестр"/>
                  </c:if>
                </div>
            </form>

        </div>


        <table>
            <tr>
                <td></td>
            </tr>
        </table>

    </div>
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