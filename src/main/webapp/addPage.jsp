<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<html lang="en">

<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="property.text" var="locale" scope="session"/>

    <fmt:message bundle="${locale}" key="text.en.locale.button" var="en_button"/>
    <fmt:message bundle="${locale}" key="text.ru.locale.button" var="ru_button"/>
    <fmt:message bundle="${locale}" key="text.add.button" var="add"/>
    <fmt:message bundle="${locale}" key="text.add.page.title" var="title"/>
    <fmt:message bundle="${locale}" key="text.link.go.main" var="link_main"/>
    <fmt:message bundle="${locale}" key="text.article.title" var="article_title"/>
    <fmt:message bundle="${locale}" key="text.article.body" var="article_body"/>

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

    <form action="/Controller" method="post">
        <input type="hidden" name="command" value="add">

        <h4>${article_title}:</h4>
        <input type="text" name="title" style="width: 200px" required><br><br>

        <h4>${article_body}:</h4>
        <textarea type="text" name="body" rows="35" cols="100"></textarea><br><br>

        <input type="submit" value="${add}">
    </form>

    <h4>${param.answer}</h4>
    <h4><a href="index.jsp">${link_main}</a></h4>
</body>

</html>
