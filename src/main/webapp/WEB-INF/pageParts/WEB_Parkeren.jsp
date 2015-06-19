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
            <%--
<table>
<% if(/* heeft reservations*/) { %>
<c:forEach>
    <% if(reservation.isActive()) { %>
    <tr><td>${reservation.dateCreated}</td><td>${reservation.arrivalDate}</td><td>${reservation.pickupDate}</td></tr>
    <% }%>
</c:forEach>
    <% } else { %>
    <p>U heeft nog geen reserveringen geplaatst</p>
    <%}%> --%>
<%@include file="/WEB-INF/view/website/footer.jsp" %>
