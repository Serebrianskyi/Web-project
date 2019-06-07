<%@ page import="com.company.view.Paths" %>
<%@ page import="com.company.view.Attributes" %>
<%@ page import="com.company.view.Parameters" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <h2>${patientId.lastName} ${patientId.firstName} ${patientId.surName}</h2>
    <table>
        <c:forEach var="i" items="${diagnosisHistoryList}">
            <tr>
                <td>
                    <c:if test="${i.diagnosisType == 'PRIMARY'}">
                    <a href=".${Paths.SHOW_ASSIGNATIONS}?${Attributes.DIAGNOSIS_HISTORY_ID}=${i.id}">
                        </c:if>
                            ${i.date} <b> ${i.diagnosis.name} </b>
                        (
                        <c:choose>
                            <c:when test="${i.staff.role == 'DOCTOR'}">
                                <fmt:message key="doctor"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="nurse"/>
                            </c:otherwise>
                        </c:choose>
                            ${i.staff.lastName} ${i.staff.firstName} ${i.staff.surName}
                        )
                        <c:if test="${i.diagnosisType == 'PRIMARY'}">
                    </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <c:choose>
        <c:when test="${isPatientOnCure}">
            <a href=".${Paths.SET_DIAGNOSIS}?id=${patientId.id}&${Parameters.DIAGNOSIS_TYPE}=FINAL"><fmt:message
                    key="patient.drawout"/></a>
        </c:when>
        <c:otherwise>
            <a href=".${Paths.SET_DIAGNOSIS}?id=${patientId.id}&${Parameters.DIAGNOSIS_TYPE}=PRIMARY"><fmt:message
                    key="patient.diagnosis.set"/></a>
        </c:otherwise>
    </c:choose>
    <br><br>
    <a href=".${Paths.SHOW_PATIENTS}"><fmt:message key="patient.back.to.patients"/></a>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>
