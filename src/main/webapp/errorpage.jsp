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
    <fmt:message bundle="${loc}" key="errorpage.message" var="message" />
    <fmt:message bundle="${loc}" key="errorpage.authorization" var="authorization" />

</head>
<body>
    ${message}
    <a href="index.jsp">${authorization}</a>
</body>
</html>
