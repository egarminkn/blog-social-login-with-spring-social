<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <c:set var="title" value="Страница ошибки" scope="request"/>
    <jsp:include page="fragments/head-tag.jsp"/>

    <body>
        <div class="container">
            <h2>Страница ошибки</h2>

            <p>
                Ошибка произошла в
                <span>
                    ${timestamp}
                </span>
            </p>

            <p>
                <div>
                    <span style="font-weight: bold">
                        HTTP Status:
                    </span>
                    <span>
                        ${status}
                    </span>
                    (<span>
                        ${error}
                    </span>)
                </div>

                <div>
                    <span style="font-weight: bold">
                        Message:
                    </span>
                    <span>
                        ${message}
                    </span>
                </div>

                <c:if test="${exception != null}">
                    <div>
                        <span style="font-weight: bold">
                            Exception:
                        </span>
                        <span>
                            ${exception}
                        </span>
                    </div>
                </c:if>

                <c:if test="${exception != null}">
                    <div>
                        <span style="font-weight: bold">
                            Trace:
                        </span>
                        <span>
                            ${trace}
                        </span>
                    </div>
                </c:if>
            </p>

            <p>
                Назад на
                <a href="/">
                    главную
                </a>
            </p>
        </div>
    </body>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" />
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" />
</html>