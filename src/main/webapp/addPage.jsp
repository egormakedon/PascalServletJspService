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
        <fmt:message bundle="${locale}" key="text.add.button" var="add"/>
        <fmt:message bundle="${locale}" key="text.add.page.title" var="title"/>
        <fmt:message bundle="${locale}" key="text.link.go.main" var="link_main"/>
        <fmt:message bundle="${locale}" key="text.article.title" var="article_title"/>
        <fmt:message bundle="${locale}" key="text.article.body" var="article_body"/>

        <title>${title}</title>
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

        <div itemscope itemtype="http://schema.org/Thing">
            <form action="/Controller" method="get">
                <input type="hidden" name="command" value="change_locale">
                <input type="hidden" name="lang" value="ru">
                <input itemprop="name" type="submit" value="${ru_button}">
            </form>
        </div>

        <div itemscope itemtype="http://schema.org/Article">
            <form action="/Controller" method="post">
                <input type="hidden" name="command" value="add">

                <h1>${article_title}:</h1>
                <input itemprop="title" type="text" name="title" style="width: 200px" required><br><br>

                <h3>${article_body}:</h3>
                <textarea type="text" itemprop="body" name="body" rows="35" cols="100"></textarea><br><br>

                <div itemscope itemtype="http://schema.org/Thing">
                    <input itemprop="name" type="submit" value="${add}">
                </div>
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
