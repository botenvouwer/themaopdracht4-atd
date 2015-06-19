<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cars" value="${requestScope.cars}" ></c:set>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<a href="/klant/reparatie" style="float:right"><input type="button" value="Terug"></a>

<h2>Reparatie Aanmaken</h2>
<p>Om een reparatie te kunnen aanmaken, moet u eerst een auto toevoegen voor de reparatie, dit kunt u <a href="/klant/auto">hier</a> doen.</p>

<form class="formstyle">
<table>
    <tr>
        <td>Reparatie Type:</td>
        <td>
            <select name="type">
                <option value="APK">APK</option>
                <option value="Reparatie">Reparatie</option>
            </select>
        </td>
    </tr>
    
    <tr>
        <td>Auto:</td>
        <td>
            <select name="car">
            <c:forEach var="car" items="${cars}">
                <option value="${car.id}">${car.licensePlate} (${car.brand} ${car.model})</option>
            </c:forEach>
            </select>
        </td>
    </tr>
    
    <tr>
        <td>Notitie:</td>
        <td>
            <textarea name="note"></textarea>
        </td>
    </tr>
    
    <tr>
        <td></td>
        <td><input style="margin: 0; max-width: none;" type="submit" value="Opslaan" /></td>
    </tr>
</table>
</form>
              
<%@include file="/WEB-INF/view/website/footer.jsp" %>