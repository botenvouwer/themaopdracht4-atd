<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Parkeren</h2>
<p>Hier kunt u een reservering plaatsen, wanneer de reserving geplaatst is wordt er automatisch een factuur opgemaakt.</p>
    <form class="formstyle" method="POST" action="/klant/parkeren">
        <table class="parkeren">
            <tr>
                <td>Aankomst Datum:</td>
                <td>
                    <input style="margin: 0;" name="arrivalDate" type="date">
                    <i class="error">${requestScope.arrivalError}</i>
                </td>
            </tr>

            <tr>
                <td>Vertrek Datum:</td>
                <td>
                    <input style="margin: 0;" name="pickupDate" type="date">
                    <i class="error">${requestScope.pickupError}</i>
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input style="margin: 0; max-width: none;" type="submit" name="send" value="Reservering plaatsen"></td>
            </tr>
        </table>
    </form>         
    
<c:choose>
    <c:when test="${!empty reservations}">
        <h2 style='margin-bottom: 5px;'>Uw Reserveringen</h2>
        <table>
            <tr class="top">
                <td>Aankomst Datum</td>
                <td>Vertrek Datum</td>
            </tr>
       
            <c:forEach var="reservation" items="${reservations}">
                <c:if test="${reservation.active()}">
                    <tr>
                        <td><fmt:formatDate timeStyle="short" type="date" value="${reservation.arrivalDate}" /></td>
                        <td><fmt:formatDate timeStyle="short" type="date" value="${reservation.pickupDate}" /></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </c:when>
        <c:when test="${empty reservations}">
            <p>Er zijn geen reserveringen gevonden.</p>
        </c:when>
</c:choose>
<%@include file="/WEB-INF/view/website/footer.jsp" %>
