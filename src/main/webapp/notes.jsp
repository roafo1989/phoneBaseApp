<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Notes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Notes</h2>
    <form method="get" action="notes">
        <input type="hidden" name="action" value="filter">
        <dl>
            <dt>Number for find:</dt>
            <dd><input type="number" name="number" value="${param.number}"></dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="notes?action=create">Add Note</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Number</th>
            <th>Comment</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${notes}" var="note">
            <jsp:useBean id="note" type="ru.home.phoneBaseApp.model.PhoneBaseNote"/>
            <tr>
                <td>${note.name}</td>
                <td>${note.number}</td>
                <td>${note.comment}</td>
                <td><a href="notes?action=update&id=${note.id}">Update</a></td>
                <td><a href="notes?action=delete&id=${note.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>