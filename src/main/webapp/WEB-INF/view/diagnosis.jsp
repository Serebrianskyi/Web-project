<%@ page import="com.company.view.Paths" %>

<%@ include file="/WEB-INF/view/includes/header.jsp" %>
<div align="center">
    <h2>${patientId.lastName} ${patientId.firstName} ${patientId.surName}</h2>
    <form method="post" action=".${Paths.ADD_DIAGNOSIS}">

        <select name="diagnosisId" required>
            <c:forEach var="i" items="${diagnosesList}">
                <option value="${i.id}">${i.name}</option>
                <br>
            </c:forEach>
        </select>
        <input type="submit">
    </form>
</div>
<%@ include file="/WEB-INF/view/includes/footer.jsp" %>