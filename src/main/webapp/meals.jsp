<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<table>
    <thead>
    <tr>
        <td>Описание</td>
        <td>Дата</td>
        <td>Калории</td>
        <td>Править</td>
        <td>Удалить</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr class="${meal.exceed ? 'exceed' : 'default'}">
            <td>${meal.description}</td>
            <td>${meal.dateTime}</td>
            <td>${meal.calories}</td>
            <td><button>Править</button></td>
            <td><button>Удалить</button></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form method="post" action="meals">
    Description
    <input type="text" name="description">
    Calories
    <input type="number" name="calories">
    Date
    <input type="datetime-local" name="dateTime">
    <input type="submit" value="Submit">
</form>
</body>
</html>
