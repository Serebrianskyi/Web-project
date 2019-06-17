<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <form method="post" action=".${Paths.ADD_PROCEDURES_APPOINTMENT}">
        <input type="hidden" name="${Parameters.DIAGNOSIS_HISTORY_ID}" value="${diagnosisHistoryId}">
        <table>
            <tr>
                <th>     </th>
                <th><fmt:message key="drugs.days"/></th>
            </tr>
            <c:forEach var="i" items="${procedures}">
                <tr>
                    <td> ${i.name} </td>
                    <td><input type="number" name="procedureDaysQuantity_${i.id}"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>