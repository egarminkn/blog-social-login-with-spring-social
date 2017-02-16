<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <title>${title}</title>

    <base href="${pageContext.request.contextPath}/"/>

    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.min.css" />
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />

    <style type="text/css">
        .container-body {
            width: 500px;
        }

        .container-header {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
        }

        .right-side {
            display: flex;
            flex-direction: row;
            justify-content: flex-start;
            align-items: center;
        }

        .navbar-text {
            line-height: 33px;
        }

        .ml.btn.btn-social-icon.btn-sm {
            display: inline-block;
            vertical-align: middle;
            font-size: 12px;
            width: 32px;
            height: 32px;
            color: white;
            text-decoration: none;
            margin: 0 10px 0 10px;
        }

        .ml.btn.btn-social-icon.btn-sm .fa {
            line-height: 32px;
        }

        .ml.btn.btn-block.btn-social-icon.btn-sm {
            width: 50px;
            height: 50px;
            margin: 0 20px 20px 0;
        }

        .ml.btn.btn-block.btn-social-icon.btn-sm .fa {
            font-size: 20px;
            line-height: 50px;
        }

        .ml.btn.btn-social-icon.btn-sm:hover,
        .ml.btn.btn-social-icon.btn-sm:focus {
            color: white;
            text-decoration: none;
        }
    </style>

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
</head>