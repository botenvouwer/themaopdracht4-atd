<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Welkom ${sessionScope.user.name}</h2>
<form method="post">
    <table class="registreer">
        <tr><td colspan="2">Uw gegevens aanpassen</td></tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.nameError}</i></td>
        </tr>
        <tr>
            <td>Naam:</td>
            <td>
                <input name="name" type="text" style="width: 400px" placeholder="${sessionScope.user.name}" value="${sessionScope.user.name}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.emailError}</i></td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td>
                <input name="email" type="text" style="width: 400px" placeholder="${sessionScope.user.email}" value="${sessionScope.user.email}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.adressError}</i></td>
        </tr>
        <tr>
            <td>Adres:</td>
            <td>
                <input name="adress" type="text" style="width: 400px" placeholder="${sessionScope.user.adress}"  value="${sessionScope.user.adress}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.zipcodeError}</i></td>
        </tr>
        <tr>
            <td>Postcode:</td>
            <td>
                <input name="zipcode" type="text" style="width: 400px" placeholder="${sessionScope.user.zipcode}"  value="${sessionScope.user.zipcode}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.placeError}</i></td>
        </tr>
        <tr>
            <td>Plaats:</td>
            <td>
                <input name="place" type="text" style="width: 400px" placeholder="${sessionScope.user.place}"  value="${sessionScope.user.place}"><br>
            </td>
        </tr>
        <tr><td colspan="2" style="text-align: center"><input type="submit" name="data_change" value="Aanpassen"></td></tr>
        <tr><td colspan="2">Wachtwoord veranderen</td></tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.oldPasswordError}</i></td>
        </tr>
        <tr>
            <td>Huidige wachtwoord</td>
            <td><input type="password" name="oldPassword" style="width: 400px" ></td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.newPasswordError}</i></td>
        </tr>
        <tr>
            <td>Nieuw wachtwoord</td>
            <td><input type="password" name="newPassword" style="width: 400px" ></td>
        </tr>
        <tr>
            <td colspan="2"><i class="error">${requestScope.repeatPasswordError}</i></td>
        </tr>
        <tr>
            <td>Herhaling</td>
            <td><input type="password" name="repeatPassword" style="width: 400px" ></td>
        </tr>
        <tr><td colspan="2" style="text-align: center"><input type="submit" name="password_change" value="Aanpassen"></td></tr>
    </table>
</form>
<%@include file="/WEB-INF/view/website/footer.jsp" %>