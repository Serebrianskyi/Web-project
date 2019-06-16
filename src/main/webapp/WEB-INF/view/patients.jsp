<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <table>
        <c:forEach var="i" items="${patientsList}">
            <tr>
                <td><a href=".${Paths.SHOW_PATIENT_INFO}?id=${i.id}">${i.lastName} ${i.firstName} ${i.surName}</a></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href=".${Paths.SHOW_ADD_PATIENT_FORM}"><fmt:message key="title.patient.add"/></a>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>