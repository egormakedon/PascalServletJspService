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

        <jsp:useBean id="bean1" class="java.lang.String" scope="page"/>
        <jsp:useBean id="bean2" class="java.lang.String" scope="page"/>

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

        <h1>Directive, tag, jstl tag:</h1>

        <%@include file="includeDirective.jsp"%>

        <br><br>

        <jsp:include page="includeTag.jsp">
            <jsp:param name="helloWorld" value="Hello, World!"/>
        </jsp:include>

        <br><br>
        <c:out value="Tag import from jstl:"/><br>
        <c:import url="https://www.w3.org/History/19921103-hypertext/hypertext/WWW/TheProject.html"/>

        <br>

        <h1>Filters drop:</h1>

        <form action="/Controller" method="get">
            Redirect to bsuir: <input type="submit" name="dropFilter" value="drop1">
            <br>
            RuntimeException: <input type="submit" name="dropFilter" value="drop2">
            <br>
            Empty return: <input type="submit" name="dropFilter" value="drop3">
            <br>
            Forward without filterChain: <input type="submit" name="dropFilter" value="drop4">
            <br>
            While true: <input type="submit" name="dropFilter" value="drop5">
            <br>
            System.exit(): <input type="submit" name="dropFilter" value="drop6">
        </form>

        <h1>useBean:</h1>
        <%bean1="1";bean2="1";%>
        ${bean1.equals(bean2)}
    </body>
</html>
