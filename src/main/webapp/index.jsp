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
        <fmt:message bundle="${locale}" key="text.index.page.title" var="title"/>
        <fmt:message bundle="${locale}" key="text.article.title" var="article_title"/>
        <fmt:message bundle="${locale}" key="text.add.button" var="add"/>

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

        <h3> <span itemprop="url"> <a href="addPage.jsp">${add}</a> </span> </h3>

        <div itemscope itemtype="http://schema.org/Thing">
            <h3> <span itemprop="name"> ${article_title}: </span> </h3>

            <div style="overflow-y: scroll; width: 120px; height: 500px">
                <form action="/Controller" method="get">
                    <input type="hidden" name="command" value="take_article">
                    <c:forEach items="${titleList}" var="title">
                        <input type="submit" name="title" value="${title}"><br>
                    </c:forEach>
                </form>
            </div>
        </div>

        <div itemscope itemtype="http://schema.org/Thing">
            <h3> <span itemprop="name">${param.answer}</span> </h3>
        </div>

        <br>

        <%@include file="includeDirective.jsp"%>

        <br><br>

        <jsp:include page="includeTag.jsp">
            <jsp:param name="helloWorld" value="Hello, World!"/>
        </jsp:include>

        <br><br>
        <c:out value="Tag import from jstl:"/><br>
        <c:import url="https://www.bsuir.by/"/>
    </body>
</html>
