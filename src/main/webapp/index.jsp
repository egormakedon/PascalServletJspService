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
    <fmt:message bundle="${locale}" key="text.index.page.title" var="title"/>
    <fmt:message bundle="${locale}" key="text.article.title" var="article_title"/>
    <fmt:message bundle="${locale}" key="text.add.button" var="add"/>

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

    <h4><a href="addPage.jsp">${add}</a></h4>

    <h4>${article_title}:</h4>
    <div style="overflow-y: scroll; width: 120px; height: 500px">
        <form action="/Controller" method="get">
            <input type="hidden" name="command" value="take_article">
            <c:forEach items="${titleList}" var="title">
                <input type="submit" name="title" value="${title}"><br>
            </c:forEach>
        </form>
    </div>

    <h4>${param.answer}</h4>
</body>

</html>
