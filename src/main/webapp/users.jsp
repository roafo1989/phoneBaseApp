<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roafo
  Date: 28.11.2019
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Users</h2>
    <form method="get" action="users">
    <input type="hidden" name="action" value="filter">
    <dl>
        <dt>Name for find:</dt>
        <dd><input type="text" name="name" value="${param.name}"></dd>
    </dl>
    <button type="submit">Filter</button>
    </form>
    <hr/>
    <a href="users?action=create">Add User</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="note" type="ru.home.phoneBaseApp.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td><a href="users?action=update&id=${user.id}">Update</a></td>
                <td><a href="users?action=delete&id=${user.id}">Delete</a></td>
            </tr>

        </c:forEach>
    </table>
</section>
</body>
</html>
