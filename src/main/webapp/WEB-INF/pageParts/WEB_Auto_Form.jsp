<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<a href="../klant" style="float:right"><input type="button" value="Terug"></a>
<form method="post">
    <table style="width:600px">
    <c:choose>
    <c:when test="${requestScope.car == null}">
        <tr><td colspan="2"><h2>Auto toevoegen</h2></td></tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.licensePlateError}</i></td>
        </tr>
        <tr>
            <td>Kentekenplaat:</td>
            <td>
                <input name="licensePlate" type="text" style="width: 400px" placeholder="${param.licensePlate}" value="${param.licensePlate}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.brandError}</i></td>
        </tr>
        <tr>
            <td>Merk:</td>
            <td>
                <input name="brand" type="text" style="width: 400px" placeholder="${param.brand}" value="${param.brand}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.modelError}</i></td>
        </tr>
        <tr>
            <td>Model:</td>
            <td>
                <input name="model" type="text" style="width: 400px" placeholder="${param.model}" value="${param.model}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="Nieuw" name="new">
            </td>
        </tr>
        </c:when>
        <c:otherwise>
        <tr><td colspan="2"><h2>Auto aanpassen</h2></td></tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.licensePlateError}</i></td>
        </tr>
        <tr>
            <td>Kentekenplaat:</td>
            <td>
                <input name="licensePlate" type="text" style="width: 400px" placeholder="${requestScope.car.licensePlate}" value="${requestScope.car.licensePlate}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.nameError}</i></td>
        </tr>
        <tr>
            <td>Merk:</td>
            <td>
                <input name="brand" type="text" style="width: 400px" placeholder="${requestScope.car.brand}" value="${requestScope.car.brand}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.nameError}</i></td>
        </tr>
        <tr>
            <td>Model:</td>
            <td>
                <input name="model" type="text" style="width: 400px" placeholder="${requestScope.car.model}" value="${requestScope.car.model}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <input type="submit" value="Aanpassen" name="edit">
            </td>
        </tr>
        </c:otherwise>
        </c:choose>
            </td>
        </tr>
    </table>
</form>
<%@include file="/WEB-INF/view/website/footer.jsp" %>