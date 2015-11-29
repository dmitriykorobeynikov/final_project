<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <fmt:setLocale value="${sessionScope.local}" />
        <fmt:setBundle basename="localization" var="loc" />
        <fmt:message bundle="${loc}" key="index.message" var="message" />
        <fmt:message bundle="${loc}" key="index.locbutton.name.ru" var="ru_button" />
        <fmt:message bundle="${loc}" key="index.locbutton.name.en" var="en_button" />
        <fmt:message bundle="${loc}" key="index.login" var="login" />
        <fmt:message bundle="${loc}" key="index.password" var="password" />
        <fmt:message bundle="${loc}" key="index.submit" var="submit" />
        <fmt:message bundle="${loc}" key="index.registration" var="registration" />

    </head>
    <body>
    <form action="IndexServlet" method="post">
        <input type="hidden" name="local" value="ru" />
        <input type="submit" value="${ru_button}"/><br/>
    </form>

    <form action="IndexServlet" method="post">
        <input type="hidden" name="local" value="en" />
        <input type="submit" value="${en_button}" /><br/>
    </form>

    <c:out value="${message}"/>

        <form action="LoginServlet" method="post">
            <input name="login" type="text" placeholder="${login}"><br>
            <input name="password" type="password" placeholder="${password}"><br>
            <input type="submit" value="${submit}">
            <a href="registration.jsp">${registration}</a>
        </form>

    </body>
</html>