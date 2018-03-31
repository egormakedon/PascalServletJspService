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
    <fmt:message bundle="${locale}" key="text.article.page.title" var="page_title"/>
    <fmt:message bundle="${locale}" key="text.link.go.main" var="link_main"/>
    <fmt:message bundle="${locale}" key="text.update.button" var="update"/>
    <fmt:message bundle="${locale}" key="text.remove.button" var="remove"/>

    <fmt:message bundle="${locale}" key="text.title" var="title"/>
    <fmt:message bundle="${locale}" key="text.date" var="date"/>
    <fmt:message bundle="${locale}" key="text.author.name" var="author_name"/>
    <fmt:message bundle="${locale}" key="text.author.surname" var="author_surname"/>
    <fmt:message bundle="${locale}" key="text.country" var="country"/>
    <fmt:message bundle="${locale}" key="text.resource.name" var="resource_name"/>
    <fmt:message bundle="${locale}" key="text.url" var="url"/>
    <fmt:message bundle="${locale}" key="text.body" var="body"/>

    <title>${page_title}</title>
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
        <input type="hidden" name="command" value="article_service">
        <input type="hidden" name="id" value="${requestScope.article.articleId}">
        <input type="hidden" name="title" value="${requestScope.article.title}">

        <h4>${title}: ${requestScope.article.title}</h4>
        <h4>${date}: ${requestScope.article.date}</h4>
        <h4>${author_name}: ${requestScope.article.author.name}</h4>
        <h4>${author_surname}: ${requestScope.article.author.surname}</h4>
        <h4>${country}: ${requestScope.article.author.country}</h4>
        <h4>${resource_name}: ${requestScope.article.resource.name}</h4>
        <h4>${url}: ${requestScope.article.resource.url}</h4>

        <h4>${body}: </h4>
        <textarea type="text" name="body" rows="25" cols="230">${requestScope.article.body}</textarea><br><br>

        <input type="submit" name="command_type" value="${update}">
        <input type="submit" name="command_type" value="${remove}">
    </form>

    <h4>${param.answer}</h4>
    <h4><a href="index.jsp">${link_main}</a></h4>
</body>

</html>
