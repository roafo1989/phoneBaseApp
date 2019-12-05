<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<nav class="navbar navbar-dark bg-dark py-0">
    <div class="container">
        <a href="notes" class="navbar-brand"><img src="resources/images/book.png"> <fmt:message key="app.title"/></a>
        <form class="form-inline my-2">
            <a class="btn btn-info mr-1" href="users"><fmt:message key="user.title"/></a>
            <a class="btn btn-primary" href="">
                <span class="fa fa-sign-in"></span>
            </a>
        </form>
    </div>
</nav>
