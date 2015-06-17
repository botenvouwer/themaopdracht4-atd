<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Welkom ${sessionScope.user.name}</h2>
<table>
    <tr>
        <td>Naam :</td>
        <td>${sessionScope.user.name}</td>
    </tr>
    <tr>
        <td>Email :</td>
        <td>${sessionScope.user.email}</td>
    </tr>
    <tr>
        <td>Adres :</td>
        <td>${sessionScope.user.adress}</td>
    </tr>
    <tr>
        <td>Postcode :</td>
        <td>${sessionScope.user.zipcode}</td>        
    </tr>
    <tr>
        <td>Plaats :</td>
        <td>${sessionScope.user.place}</td>
    </tr>
</table>
<p>Uw Auto's</p>
<table>
    <tr><td>ID</td><td>Kentekenplaat</td><td>Merk</td><td>Model</td><td></td></tr>
    <c:forEach var="car" items="${cars}">
        <tr><td>${car.id}</td><td>${car.licensePlate}</td><td>${car.brand}</td><td>${car.model}</td><td><a href="#">Aanpassen</a></td></tr>
    </c:forEach>
</table>
<a href="klant_form"><input type="button" value="Gegevens aanpassen"></a><a href="#"><input type="button" value="Auto toevoegen"></a>
<%@include file="/WEB-INF/view/website/footer.jsp" %>