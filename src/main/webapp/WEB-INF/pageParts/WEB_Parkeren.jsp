<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Parkeren</h2>
<p>Hier kunt u een reservering plaatsen voor het parkeren bij ons.</p>
    <form method="POST" action="/klant/parkeren">
        <table class="parkeren">
            <tr>
                <td>Aankomst datum:</td>
                <td><input name="arrivalDate" type="date"></td>
            </tr>
            <tr><td colspan="2"><i class="error">${requestScope.arrivalError}</i></td></tr>
            <tr>
                <td>Vertrek datum: </td>
                <td><input name="pickupDate" type="date"></td>
            </tr>
            <tr><td colspan="2"><i class="error">${requestScope.pickupError}</i></td></tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" name="placeReservation" value="Reservering plaatsen"></td>
            </tr>
        </table>
    </form>
            
<table>
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
            <p>Er zijn geen reserveringen gevonden</p>
        </c:when>
</c:choose>
<%@include file="/WEB-INF/view/website/footer.jsp" %>
