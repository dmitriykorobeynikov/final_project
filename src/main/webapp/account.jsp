<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="mytag" uri="/WEB-INF/tld/mytaglib.tld" %>

<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization" var="loc" />
    <fmt:message bundle="${loc}" key="account.welcome" var="welcome" />
    <fmt:message bundle="${loc}" key="account.logout" var="logout" />
    <fmt:message bundle="${loc}" key="account.orders.message" var="orders_message" />
    <fmt:message bundle="${loc}" key="account.orders.type_of_work" var="type_of_work" />
    <fmt:message bundle="${loc}" key="account.orders.volume" var="volume" />
    <fmt:message bundle="${loc}" key="account.orders.finish_date" var="finish_date" />
    <fmt:message bundle="${loc}" key="account.orders.add_new_order" var="add_new_order" />

</head>

<body>
${welcome}, ${sessionScope.login}!
<form action="LogoutServlet" method="post">
    <input type="submit" value="${logout}">
</form>
${orders_message} <br>

<table>
    <thead><tr><th>${type_of_work}</th><th>${volume}</th><th>${finish_date}</th></tr></thead>
    <tbody>
    <c:forEach items="${requestScope.orders}" var="order">
        <mytag:UserOrderTag order="${order}" local="${sessionScope.local}"/>
    </c:forEach>
    </tbody>
</table>

<a href="addneworder.jsp">${add_new_order}</a>


</body>
</html>
