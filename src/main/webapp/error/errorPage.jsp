<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html lang="en">
<head>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="property.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.errorPage.title" var="title"/>
    <fmt:message bundle="${locale}" key="text.en.locale.button" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.ru.locale.button" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.errorPage.request.from" var="request_from"/>
    <fmt:message bundle="${locale}" key="text.errorPage.servlet.name" var="servlet_name"/>
    <fmt:message bundle="${locale}" key="text.errorPage.status.code" var="status_code"/>
    <fmt:message bundle="${locale}" key="text.errorPage.exception" var="exception"/>
    <fmt:message bundle="${locale}" key="text.errorPage.stack.trace" var="stack_trace"/>
    <fmt:message bundle="${locale}" key="text.link.go.main" var="link_main"/>

    <title>${title}</title>

</head>

<body>
    <form action="/Controller" method="get">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="lang" value="en">
        <input type="submit" value="${en_button}">
    </form>

    <form action="/Controller" method="get">
        <input type="hidden" name="command" value="change_locale">
        <input type="hidden" name="lang" value="ru">
        <input type="submit" value="${ru_button}">
    </form>

    <h4>${request_from}: ${pageContext.errorData.requestURI}</h4><br/>
    <h4>${servlet_name}: ${pageContext.errorData.servletName}</h4><br/>
    <h4>${status_code}: ${pageContext.errorData.statusCode}</h4><br/>
    <h4>${exception}: ${pageContext.exception.toString()}</h4><br/>
    <h4><a href="../index.jsp">${link_main}</a></h4>
</body>

</html>