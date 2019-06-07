%@ page errorPage="/WEB-INF/com.company.view/errorPage/errorPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setBundle basename="${sessionScope[Attributes.BUNDLE_FILE]}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <title><fmt:message key="${page_title}"/></title>
</head>

<body>
<div align="center">
    <br>
    <div class="container" align="center">

        <%@ include file="/WEB-INF/view/i18n/languages.jsp" %>
        <br>
        <div align="center">
            <img src="${pageContext.request.contextPath}/img/hospital.jpg" height="100px" alt="Hospital"><br>
            <h1><fmt:message key="${page_title}"/></h1>
        </div>
