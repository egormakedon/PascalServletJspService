<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
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

        <div itemscope itemtype="http://schema.org/Thing">
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="change_locale">
                <input type="hidden" name="lang" value="en">
                <input itemprop="name" type="submit" value="${en_button}">
            </form>
        </div>

        <br>

        <div>
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="change_locale">
                <input type="hidden" name="lang" value="ru">
                <input itemprop="name" type="submit" value="${ru_button}">
            </form>
        </div>

        <div itemscope itemtype="http://schema.org/Article">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="article_service">
                <input type="hidden" name="id" value="${requestScope.article.articleId}">
                <input type="hidden" name="title" value="${requestScope.article.title}">

                <h1> ${title}: <span itemprop="title"> ${requestScope.article.title} </span> </h1>
                <h3> ${date}: <span itemprop="date"> ${requestScope.article.date} </span> </h3>

                <div itemprop="author" itemscope itemtype="http://schema.org/Article">
                    <h3> ${author_name}: <span itemprop="name"> ${requestScope.article.author.name} </span> </h3>
                    <h3> ${author_surname}: <span itemprop="surname"> ${requestScope.article.author.surname} </span> </h3>
                    <h3> ${country}: <span itemprop="country"> ${requestScope.article.author.country} </span> </h3>
                </div>

                <div itemprop="resource" itemscope itemtype="http://schema.org/Resource">
                    <h3> ${resource_name}: <span itemprop="name"> ${requestScope.article.resource.name} </span> </h3>
                    <h3> ${url}: <span itemprop="url"> ${requestScope.article.resource.url} </span> </h3>
                </div>

                <h3>${body}: </h3>
                <textarea type="text" itemprop="nody" name="body" rows="25" cols="230">${requestScope.article.body}</textarea><br><br>

                <input type="submit" name="command_type" value="${update}">
                <input type="submit" name="command_type" value="${remove}">
            </form>
        </div>

        <div itemscope itemtype="http://schema.org/Thing">
            <h3> <span itemprop="name">${param.answer}</span> </h3>
        </div>

        <div itemscope itemtype="http://schema.org/Thing">
            <h3> <a href="index.jsp"><span itemprop="name">${link_main}</span> </a> </h3>
        </div>

    </body>
</html>
