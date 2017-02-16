<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <c:set var="title" value="index" scope="request"/>
    <jsp:include page="fragments/head-tag.jsp"/>

    <body>
        <jsp:include page="fragments/user-info.jsp"/>

        <div class="container container-body">
            <h2>index</h2>

            <p>Зайти через:</p>
            <jsp:include page="fragments/login-links.jsp"/>

            <jsp:include page="fragments/error-info.jsp"/>
        </div>
    </body>

    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" />
</html>