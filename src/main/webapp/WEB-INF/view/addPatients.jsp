<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.ADD_PATIENT}">
        <label><fmt:message key="patient.new.lastname"/></label>
        <input type="text" name="lastname" required/><br>
        <label><fmt:message key="patient.new.firstname"/></label>
        <input type="text" name="firstname" required/><br>
        <label><fmt:message key="patient.new.surname"/></label>
        <input type="text" name="surname"/><br>
        <input type="submit" value="<fmt:message key="patient.new.submit"/>">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>