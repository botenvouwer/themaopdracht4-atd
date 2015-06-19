<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="tasks" value="${requestScope.tasks}" ></c:set>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Reparatie</h2>

<c:choose>
    <c:when test="${!empty tasks}">
        <p>Hieronder kunt u al uw reparaties zien, klik <a href="/klant/reparatie/form">hier</a> om een reparatie aan te melden.</p>
        
        <table>
            <tr class="top">
                <td style="width: 60px;">Type</td>
                <td style="width: 60px;">Status</td>
                <td>Auto</td>
                <td>Notitie</td>
            </tr>

            <c:forEach var="task" items="${tasks}">
                <tr>
                    <td>${task.type}</td>
                    <td>${task.status}</td>
                    <td>${task.car.licensePlate} (${task.car.brand} ${task.car.model})</td>
                    <td>${task.customerNote}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    
    <c:when test="${empty tasks}">
        <p>Er zijn geen reparaties gevonden, klik <a href="/klant/reparatie/form">hier</a> om een reparatie aan te melden.</p>
    </c:when>
</c:choose>

<%@include file="/WEB-INF/view/website/footer.jsp" %>