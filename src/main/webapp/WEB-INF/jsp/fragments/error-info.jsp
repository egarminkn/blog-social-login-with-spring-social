<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div>
    <p>
        <c:if test="${exception != null}">
            <div style="color: red">
                <span style="font-weight: bold">
                    Exception:
                </span>
                <span>
                    ${exception}
                </span>
            </div>
        </c:if>
    </p>
</div>