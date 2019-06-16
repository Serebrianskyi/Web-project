<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<a href=".${Paths.SHOW_LOGIN_FORM}"><fmt:message key="login"/></a>
<br>
<a href=".${Paths.SHOW_REGISTRATION_FORM}"><fmt:message key="registration"/></a>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>