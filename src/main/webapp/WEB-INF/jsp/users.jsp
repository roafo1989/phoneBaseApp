<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<section>
    <h3><a href="${pageContext.request.contextPath}">Home</a></h3>
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
    <a href="users/create">Add User</a>
    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Active</th>
            <th>Registered</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${users}" var="user">
            <jsp:useBean id="user" type="ru.home.phoneBaseApp.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.roles}</td>
                <td>${user.enabled}</td>
                <td><fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/></td>
                <td><a href="users/update?id=${user.id}">Update</a></td>
                <td><a href="users/delete?id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
