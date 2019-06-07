<%@ page import="com.company.view.Paths" %>
<%@ page import="com.company.view.Parameters" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <h2><fmt:message key="drugs"/></h2>
    <table>
        <c:forEach var="i" items="${drugAppointments}">
            <tr>
                <td>${i.drug.name}</td>
                <td>${i.unitsQuantity} <fmt:message key="drugs.units"/></td>
                <td>${i.dailyQuantity} <fmt:message key="drugs.times"/></td>
                <td>${i.daysQuantity} <fmt:message key="drugs.days"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href=".${Paths.SHOW_ADD_DRUGS_APPOINTMENT_FORM}?${Parameters.DIAGNOSIS_HISTORY_ID}=${historyId}">
        <fmt:message key="drugs.add"/>
    </a>

    <h2><fmt:message key="procedures"/></h2>
    <table>
        <c:forEach var="i" items="${procedureAppointments}">
            <tr>
                <td>${i.procedure.name}</td>
                <td>${i.daysQuantity} <fmt:message key="drugs.days"/></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href=".${Paths.SHOW_ADD_PROCEDURES_APPOINTMENT_FORM}?${Parameters.DIAGNOSIS_HISTORY_ID}=${historyId}">
        <fmt:message key="procedures.add"/>
    </a>

    <h2><fmt:message key="surgeries"/></h2>
    <table>
        <c:forEach var="i" items="${surgeryAppointments}">
            <tr>
                <td>${i.surgery.name}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href=".${Paths.SHOW_ADD_SURGERIES_APPOINTMENT_FORM}?${Parameters.DIAGNOSIS_HISTORY_ID}=${historyId}">
        <fmt:message key="surgeries.add"/>
    </a>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>