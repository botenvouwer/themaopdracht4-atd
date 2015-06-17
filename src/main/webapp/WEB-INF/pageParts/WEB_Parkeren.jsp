<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Parkeren</h2>
<p>Hier kunt u een reservering plaatsen voor het parkeren bij ons.</p>
<%--<div class="tableWrap content">--%>
    <form method="POST" action="/user/parkeren">
        <table class="parkeren">
            <tr>
                <td>Aankomst datum:</td>
                <td><input name="arrivalDate" type="date"></td>
            </tr>
            <tr>
                <td>Vertrek datum: </td>
                <td><input name="pickupDate" type="date"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" name="placeReservation" value="Reservering plaatsen"></td>
            </tr>
        </table>
    </form>
<%--</div>--%>

<%@include file="/WEB-INF/view/website/footer.jsp" %>
