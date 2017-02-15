<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <c:set var="title" value="Статус соединений с соц.сетями" scope="request"/>
    <jsp:include page="../fragments/head-tag.jsp"/>

    <body>
        <h3 class="center">Ваши соединения</h3>

        <c:forEach var="providerId" items="${providerIds}">
            <c:set var="connectionList" value="${connectionMap[providerId]}" />
            <c:url value="/connect/${providerId}" var="connectUrl" />

            <c:choose>
                <c:when test="${providerId == 'linkedin'}">
                    <c:set var="providerName" value="LinkedIn" />
                    <c:set var="btnClass" value="btn-linkedin" />
                    <c:set var="iconFaClass" value="fa-linkedin" />
                </c:when>
                <c:when test="${providerId == 'facebook'}">
                    <c:set var="providerName" value="Facebook" />
                    <c:set var="btnClass" value="btn-facebook" />
                    <c:set var="iconFaClass" value="fa-facebook" />
                </c:when>
                <c:when test="${providerId == 'twitter'}">
                    <c:set var="providerName" value="Twitter" />
                    <c:set var="btnClass" value="btn-twitter" />
                    <c:set var="iconFaClass" value="fa-twitter" />
                </c:when>
                <c:when test="${providerId == 'vkontakte'}">
                    <c:set var="providerName" value="ВКонтакте" />
                    <c:set var="btnClass" value="btn-vk" />
                    <c:set var="iconFaClass" value="fa-vk" />
                </c:when>
                <c:when test="${providerId == 'odnoklassniki'}">
                    <c:set var="providerName" value="Одноклассники" />
                    <c:set var="btnClass" value="btn-odnoklassniki" />
                    <c:set var="iconFaClass" value="fa-odnoklassniki" />
                </c:when>
                <c:when test="${providerId == 'google'}">
                    <c:set var="providerName" value="Google" />
                    <c:set var="btnClass" value="btn-google" />
                    <c:set var="iconFaClass" value="fa-google-plus" />
                </c:when>
            </c:choose>

            <div class="accountConnection center">
                <div class="ml btn btn-social btn-sm ${btnClass}">
                    <i class="fa ${iconFaClass}"></i>${providerName}
                </div>

                <c:if test="${empty connectionList}">
                    <form action="${connectUrl}" method="POST" class="connectForm">
                        <div class="formInfo">
                            Вы еще не зашли через ${providerName}.
                        </div>
                        <button class="connectButton" type="submit">
                            Войти
                        </button>
                    </form>
                </c:if>

                <c:if test="${not empty connectionList}">
                    <c:set var="connection" value="${connectionList.getFirst()}"/>
                    <form action="${connectUrl}" method="POST">
                        <p>
                            Вы зашли в ${providerName} как ${connection.displayName}
                            <img src="${connection.imageUrl}" width="32" height="32">
                        </p>
                        <button class="disconnectButton" type="submit">
                            Выйти
                        </button>
                        <input type="hidden" name="_method" value="delete" />
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </body>
</html>