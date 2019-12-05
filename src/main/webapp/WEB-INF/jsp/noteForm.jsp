<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Note</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create note' : 'Edit note'}</h2>
    <jsp:useBean id="note" type="ru.home.phoneBaseApp.model.Note" scope="request"/>
    <form method="post" action="notes">
        <input type="hidden" name="id" value="${note.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${note.name}" size=40 name="name" required></dd>
        </dl>
        <dl>
            <dt>Phone Number:</dt>
            <dd><input type="number" value="${note.number}" name="number" required></dd>
        </dl>
        <dl>
            <dt>Comment:</dt>
            <dd><input type="comment" value="${note.comment}" name="comment"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
