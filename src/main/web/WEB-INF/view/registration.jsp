<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div>
    <form method="post" action=".${Paths.REGISTRATION}">
        <label><fmt:message key="firstname"/></label>
        <br>
        <input type="text" name="firstname"/>
        <br>
        <label><fmt:message key="lastname"/></label>
        <br>
        <input type="text" name="lastname"/>
        <br>
        <label><fmt:message key="surname"/></label>
        <br>
        <input type="text" name="surname"/>
        <br>
        <label><fmt:message key="role"/></label>
        <br>
        <input type="text" name="role"/>
        <br>
        <label><fmt:message key="email"/></label>
        <br>
        <input type="text" name="login"/>
        <br>
        <label><fmt:message key="password"/></label>
        <br>
        <input type="password" name="password"/>
        <br>
        <input type="submit" value="<fmt:message key="registration"/>">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>
