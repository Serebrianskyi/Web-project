<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div>
    <form method="post" action=".${Paths.LOGIN}">
        <label><fmt:message key="email"/></label>
        <br>
        <input type="text" name="login"/>
        <br>
        <label><fmt:message key="password"/></label>
        <br>
        <input type="password" name="password"/>
        <br>
        <input type="submit" value="<fmt:message key="login"/>">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>
