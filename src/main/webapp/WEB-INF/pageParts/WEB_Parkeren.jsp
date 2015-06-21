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
        <p>Hieronder kunt u al uw reserveringen zien.</p>
        <table> 
            <thead>Aankomst datum</thead>
            <thead>Vertrek datum</thead>
            <thead>Datum aangemaakt</thead>
       
        <c:forEach var="reservation" items="${reservations}">
            <c:when test="${reservation.active()}">
            <tr>
            <td>${reservation.arrivalDate}</td>
            <td>${reservation.pickupDate}</td>
            <td>${reservation.dateCreated}</td>
            </tr>
            </c:when>
        </c:forEach>
        </table>
    </c:when>
        <c:when test="${empty reservations}">
            <p>Er zijn geen reserveringen gevonden.</p>
        </c:when>
</c:choose>
<%@include file="/WEB-INF/view/website/footer.jsp" %>
