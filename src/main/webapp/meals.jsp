<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h2>Meal list</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr>
    <table border="1" cellpadding="8" cellspacing="0">
        <p>Filter by:</p>
        <jsp:useBean id="filter" class="ru.javawebinar.topjava.filters.MealsFilter" scope="request"/>
        <form method="post" action="meals">
            <label for="dateFrom">From date</label>
            <input type="date" value="${filter.dateFrom}" id="dateFrom" name="dateFrom">
            <label for="dateTo">To date</label>
            <input type="date" value="${filter.dateTo}" id="dateTo" name="dateTo">
            <label for="timeFrom">From time</label>
            <input type="time" value="${filter.timeFrom}" id="timeFrom" name="timeFrom">
            <label for="timeTo">To time</label>
            <input type="time" value="${filter.timeTo}" id="timeTo" name="timeTo">
            <button type="submit" name="filtered">Apply</button>
        </form>
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.filter.toLocalDate()} ${meal.filter.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
