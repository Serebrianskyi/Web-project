<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.company.view.Attributes" %>
<%@ page import="com.company.view.Parameters" %>
<%@ page import="com.company.i18n.SupportedLocale" %>

<div align="right">
    <c:forEach items="${SupportedLocale.values()}" var="locale">
        <c:choose>
            <c:when test="${locale.locale == sessionScope[Attributes.USER_LOCALE]}">
                <b>${locale}</b>
            </c:when>
            <c:otherwise>
                <a href="?${Parameters.USER_LOCALE}=${locale.param}">${locale}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>