<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <c:set var="title" value="Страница входа" scope="request"/>
    <jsp:include page="fragments/head-tag.jsp"/>

    <body>
        <jsp:include page="fragments/user-info.jsp"/>

        <div class="container">
            <h2>Страница входа</h2>

            <p>Зайти через:</p>

            <table style="border-collapse: separate; border-spacing: 2px">
                <tr>
                    <td>
                        <a href="/auth/linkedin" class="ml btn btn-block btn-social-icon btn-sm btn-linkedin">
                            <i class="fa fa-linkedin"></i>
                        </a>
                    </td>
                    <td>
                        <a href="/auth/facebook" class="ml btn btn-block btn-social-icon btn-sm btn-facebook">
                            <i class="fa fa-facebook"></i>
                        </a>
                    </td>
                </tr>

                <tr>
                    <td>
                        <a href="/auth/twitter" class="ml btn btn-block btn-social-icon btn-sm btn-twitter">
                            <i class="fa fa-twitter"></i>
                        </a>
                    </td>
                    <td>
                        <a href="/auth/google?scope=profile" class="ml btn btn-block btn-social-icon btn-sm btn-google">
                            <i class="fa fa-google-plus"></i>
                        </a>
                    </td>
                </tr>

                <tr>
                    <td>
                        <a href="/auth/odnoklassniki" class="ml btn btn-block btn-social-icon btn-sm btn-odnoklassniki">
                            <i class="fa fa-odnoklassniki"></i>
                        </a>
                    </td>
                    <td>
                        <a href="/auth/vkontakte" class="ml btn btn-block btn-social-icon btn-sm btn-vk">
                            <i class="fa fa-vk"></i>
                        </a>
                    </td>
                </tr>
            </table>

            <jsp:include page="fragments/error-info.jsp"/>

        </div>
    </body>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" />
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js" />
</html>