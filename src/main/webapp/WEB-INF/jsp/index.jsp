<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<br>
<section>
    <form method="post" action="users">
        <select name="userId">
        <option value="100000" selected>User</option>
        <option value="100001">Admin</option>
    </select>
        <button type="submit">select</button>
    </form>
</section>
</body>
</html>