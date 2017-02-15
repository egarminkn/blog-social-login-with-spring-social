<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div>
    <!-- Replace navbar-default with navbar-inverse to get a black header -->
    <nav class="navbar navbar-default" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand">
                    Вход через соц.сети
                </a>
            </div>

            <div>
                <c:if test="${currentUserConnection != null}">
                    <p class="navbar-text navbar-right">
                        Вход выполнен через

                        <c:choose>
                            <c:when test="${currentUserConnection.providerId == 'linkedin'}">
                                <a class="ml btn btn-social-icon btn-sm btn-linkedin">
                                    <i class="fa fa-linkedin"></i>
                                </a>
                            </c:when>
                            <c:when test="${currentUserConnection.providerId == 'facebook'}">
                                <a class="ml btn btn-social-icon btn-sm btn-facebook">
                                    <i class="fa fa-facebook"></i>
                                </a>
                            </c:when>
                            <c:when test="${currentUserConnection.providerId == 'twitter'}">
                                <a class="ml btn btn-social-icon btn-sm btn-twitter">
                                    <i class="fa fa-twitter"></i>
                                </a>
                            </c:when>
                            <c:when test="${currentUserConnection.providerId == 'vkontakte'}">
                                <a class="ml btn btn-social-icon btn-sm btn-vk">
                                    <i class="fa fa-vk"></i>
                                </a>
                            </c:when>
                            <c:when test="${currentUserConnection.providerId == 'odnoklassniki'}">
                                <a class="ml btn btn-social-icon btn-sm btn-odnoklassniki">
                                    <i class="fa fa-odnoklassniki"></i>
                                </a>
                            </c:when>
                            <c:when test="${currentUserConnection.providerId == 'google'}">
                                <a class="ml btn btn-social-icon btn-sm btn-google">
                                    <i class="fa fa-google-plus"></i>
                                </a>
                            </c:when>
                        </c:choose>

                        от имени

                        <span>
                            ${currentUserDisplayName}
                        </span>
                        <img src="${currentUserConnection.imageUrl}" width="32" height="32">
                    </p>
                </c:if>
            </div>
        </div>
    </nav>
</div>