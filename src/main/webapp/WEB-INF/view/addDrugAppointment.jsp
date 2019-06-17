<%@ page import="com.company.view.Paths" %>
<%@ page import="com.company.view.Parameters" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.ADD_DRUGS_APPOINTMENT}">
        <input type="hidden" name="${Parameters.DIAGNOSIS_HISTORY_ID}" value="${diagnosisHistoryId}">
        <table>
            <tr>
                <th></th>
                <th><fmt:message key="drugs.units"/></th>
                <th><fmt:message key="drugs.times"/></th>
                <th><fmt:message key="drugs.days"/></th>
            </tr>
            <c:forEach var="i" items="${drugsList}">
                <tr>
                    <td> ${i.name} </td>
                    <td><input type="number" name="drugUnitsQuantity_${i.id}"></td>
                    <td><input type="number" name="drugDailyQuantity_${i.id}"></td>
                    <td><input type="number" name="drugDaysQuantity_${i.id}"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>