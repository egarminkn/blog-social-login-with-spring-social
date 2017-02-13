<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <c:set var="title" value="Главная страница" scope="request"/>
    <jsp:include page="fragments/head-tag.jsp"/>

    <body>
        <jsp:include page="fragments/user-info.jsp"/>

        <div class="container">
            <h2>Главная страница</h2>

            <p>
                <form method="POST" action="/update">
                    <input type="text" name="data" value="${currentData}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button>
                        Обновите ваши данные
                    </button>
                </form>
            </p>

            <p>
                <form method="POST" action="/logout">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button>
                        Разлогиниться
                    </button>
                </form>
            </p>

            <jsp:include page="fragments/error-info.jsp"/>

        </div>
    </body>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" />
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" />
</html>