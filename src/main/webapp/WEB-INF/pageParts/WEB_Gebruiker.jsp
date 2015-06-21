<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Welkom ${sessionScope.user.name}</h2>
<table>
    <tr>
        <td>Klantnummer :</td>
        <td>P<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${sessionScope.user.id}" /></td>
    </tr>
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
    <tr class="top"><td>Kentekenplaat</td><td>Merk</td><td>Model</td><td></td><td></td></tr>
    <c:forEach var="car" items="${cars}">
        <tr><td>${car.licensePlate}</td><td>${car.brand}</td><td>${car.model}</td><td><a href="klant/auto?id=${car.id}">Aanpassen</a></td><td><a href="klant?delete=${car.id}">Verwijder</a></td></tr>
    </c:forEach>
</table>
<a href="klant_form"><input type="button" value="Gegevens aanpassen"></a><a href="klant/auto"><input type="button" value="Auto toevoegen"></a>
<%@include file="/WEB-INF/view/website/footer.jsp" %>