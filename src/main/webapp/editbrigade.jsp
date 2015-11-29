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
    <fmt:message bundle="${loc}" key="editbrigade.involved_employers" var="involved_employers" />
    <fmt:message bundle="${loc}" key="editbrigade.uninvolved_employers" var="uninvolved_employers" />
    <fmt:message bundle="${loc}" key="orders.address" var="address" />
    <fmt:message bundle="${loc}" key="orders.to_orders_list" var="to_orders_list" />
    <fmt:message bundle="${loc}" key="employers.name" var="employers_name" />
    <fmt:message bundle="${loc}" key="employers.profession" var="employers_profession" />

    <fmt:message bundle="${loc}" key="account.logout" var="logout" />
    <fmt:message bundle="${loc}" key="account.orders.message" var="orders_message" />
    <fmt:message bundle="${loc}" key="account.orders.type_of_work" var="type_of_work" />
    <fmt:message bundle="${loc}" key="account.orders.volume" var="volume" />
    <fmt:message bundle="${loc}" key="account.orders.finish_date" var="finish_date" />
    <fmt:message bundle="${loc}" key="account.orders.add_new_order" var="add_new_order" />


</head>
<body>

<table>
    <thead><tr><th>${address}</th><th>${type_of_work}</th><th>${volume}</th><th>${finish_date}</th></tr></thead>
    <tbody>
        <mytag:AdminOrderTag order="${requestScope.order}" button="false" local="${sessionScope.local}"/>
</tbody>
</table>

${involved_employers}:
<table>
    <thead><tr><th>${employers_name}</th><th>${employers_profession}</th></tr></thead>
    <tbody>
    <c:forEach items="${requestScope.involvedEmployers}" var="employer">
        <mytag:EmployerTag employer="${employer}" involved="true" local="${sessionScope.local}"/>
    </c:forEach>
    </tbody>
</table>

${uninvolved_employers}:
<table>
    <thead><tr><th>${employers_name}</th><th>${employers_profession}</th></tr></thead>
    <tbody>
    <c:forEach items="${requestScope.unInvolvedEmployers}" var="employer">
        <mytag:EmployerTag employer="${employer}" involved="false" local="${sessionScope.local}"/>
    </c:forEach>
    </tbody>
</table>

<a href="dispatcher.jsp">${to_orders_list}</a>

</body>
</html>
