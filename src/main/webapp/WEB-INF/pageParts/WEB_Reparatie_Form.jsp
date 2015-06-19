<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cars" value="${requestScope.cars}" ></c:set>
<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<a href="/klant/reparatie" style="float:right"><input type="button" value="Terug"></a>

<h2>Reparatie Aanmaken</h2>

<c:choose>
    <c:when test="${!empty cars}">
        <p>Als uw reperatie is aangemaakt moet deze eerst worden goedgekeurd door ATD, u ontvangt hiervan een e-mail.</p>
        
        <form class="formstyle" method="POST" action="/klant/reparatie/form">
            <table>
                <tr>
                    <td>Reparatie Type:</td>
                    <td>
                        <select id="type" name="type">
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

                <tr id="repair" style="display: none;">
                    <td>Notitie:</td>
                    <td>
                        <textarea name="note"></textarea>
                    </td>
                </tr>

                <tr>
                    <td></td>
                    <td><input style="margin: 0; max-width: none;" name="send" type="submit" value="Opslaan" /></td>
                </tr>
            </table>
        </form>

        <script>
            $('#type').on('change', function() {
                if ($(this).val() == 'Reparatie') {
                    $('#repair').show();
                } else {
                    $('#repair').hide();
                }
            });
        </script>
    </c:when>
    
    <c:when test="${empty cars}">
        <p>Om een reparatie te kunnen aanmaken, moet u eerst een auto toevoegen voor de reparatie, dit kunt u <a href="/klant/auto">hier</a> doen.</p>
    </c:when>
</c:choose>
              
<%@include file="/WEB-INF/view/website/footer.jsp" %>