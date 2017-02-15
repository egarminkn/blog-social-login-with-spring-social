<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="pageName" value="${requestScope['javax.servlet.forward.request_uri']}"/>

<c:set var="req" value="${pageContext.request}"/>
<c:set var="siteUrl" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"/>

<h2>Форма</h2>

<form method="post" action="auth" id="login-form">
    <button type="submit" id="linkedin" class="ml btn btn-block btn-social-icon btn-sm btn-linkedin">
        <i class="fa fa-linkedin"></i>
    </button>
    <button type="submit" id="facebook" class="ml btn btn-block btn-social-icon btn-sm btn-facebook">
        <i class="fa fa-facebook"></i>
    </button>
    <button type="submit" id="twitter" class="ml btn btn-block btn-social-icon btn-sm btn-twitter">
        <i class="fa fa-twitter"></i>
    </button>
    <button type="submit" id="google" class="ml btn btn-block btn-social-icon btn-sm btn-google">
        <i class="fa fa-google-plus"></i>
    </button>
    <button type="submit" id="odnoklassniki" class="ml btn btn-block btn-social-icon btn-sm btn-odnoklassniki">
        <i class="fa fa-odnoklassniki"></i>
    </button>
    <button type="submit" id="vkontakte" class="ml btn btn-block btn-social-icon btn-sm btn-vk">
        <i class="fa fa-vk"></i>
    </button>
</form>

<script type="text/javascript">
    <%--$('#login-form').append("<input type='hidden' name='pageName' value='${pageName}'>");--%>
    <%--$('#login-form').append("<input type='hidden' name='siteUrl' value='${siteUrl}'>");--%>
    $('#login-form').append("<input type='hidden' name='redirect_uri' value='${siteUrl}${pageName}'>");

    $('#linkedin').click(function () {
        $('#login-form').append("<input type='hidden' name='providerId' value='linkedin'>");
    });

    $('#facebook').click(function () {
        $('#login-form').append("<input type='hidden' name='providerId' value='facebook'>");
    });

    $('#twitter').click(function () {
        $('#login-form').append("<input type='hidden' name='providerId' value='twitter'>");
    });

    $('#google').click(function () {
        $('#login-form').append("<input type='hidden' name='scope' value='profile'>");
        $('#login-form').append("<input type='hidden' name='providerId' value='google'>");
    });

    $('#odnoklassniki').click(function () {
        $('#login-form').append("<input type='hidden' name='providerId' value='odnoklassniki'>");
    });

    $('#vkontakte').click(function () {
        $('#login-form').append("<input type='hidden' name='providerId' value='vkontakte'>");
    });
</script>

<h2>Ссылки</h2>

<a href="/auth/linkedin" class="ml btn btn-block btn-social-icon btn-sm btn-linkedin">
    <i class="fa fa-linkedin"></i>
</a>
<a href="/auth/facebook" class="ml btn btn-block btn-social-icon btn-sm btn-facebook">
    <i class="fa fa-facebook"></i>
</a>
<a href="/auth/twitter" class="ml btn btn-block btn-social-icon btn-sm btn-twitter">
    <i class="fa fa-twitter"></i>
</a>
<a href="/auth/google?scope=profile" class="ml btn btn-block btn-social-icon btn-sm btn-google">
    <i class="fa fa-google-plus"></i>
</a>
<a href="/auth/odnoklassniki" class="ml btn btn-block btn-social-icon btn-sm btn-odnoklassniki">
    <i class="fa fa-odnoklassniki"></i>
</a>
<a href="/auth/vkontakte" class="ml btn btn-block btn-social-icon btn-sm btn-vk">
    <i class="fa fa-vk"></i>
</a>