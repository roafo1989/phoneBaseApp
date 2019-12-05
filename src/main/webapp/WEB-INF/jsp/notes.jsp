<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><fmt:message key="note.title"/></h3>
        <button class="btn btn-primary">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

<%--        <form method="get" action="notes">
            <input type="hidden" name="action" value="filter">
            <dl>
                <dt>Number for find:</dt>
                <dd><input type="number" name="number" value="${param.number}"></dd>
            </dl>
            <button type="submit">Filter</button>
        </form>--%>
        <table class="table table-striped mt-3">
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
                <jsp:useBean id="note" type="ru.home.phoneBaseApp.model.Note"/>
                <tr>
                    <td>${note.name}</td>
                    <td>${note.number}</td>
                    <td>${note.comment}</td>
                    <td><a href="notes/update?id=${note.id}">Update</a></td>
                    <td><a href="notes/delete?id=${note.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>