<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Registreren</h2>
<p>Nieuwe klanten kunnen zich hier registreren!</p>
<form action="./registreer" method="POST">
    <table class="registreer">
        <tr>
            <td colspan="2">${requestScope.nameError}</td>
        </tr>
        <tr>
            <td>Naam:</td>
            <td>
                <input name="name" type="text" style="width: 400px" placeholder="Pipo Appel" value="${param.name}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.emailError}</td>
        </tr>
        <tr>
            <td>E-mail:</td>
            <td>
                <input name="email" type="text" style="width: 400px" placeholder="kees@atd.nl" value="${param.email}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.passwordError}</td>
        </tr>
        <tr>
            <td>Wachtwoord:</td>
            <td>
                <input name="password" type="password" style="width: 400px" placeholder="******"><br>
            </td>
        </tr>
        <tr>
            <td>Herhaal wachtwoord:</td>
            <td>
                <input name="repeat" type="password" style="width: 400px" placeholder="******"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.adressError}</td>
        </tr>
        <tr>
            <td>Adres:</td>
            <td>
                <input name="adress" type="text" style="width: 400px" placeholder="Zate 31"  value="${param.adress}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.zipcodeError}</td>
        </tr>
        <tr>
            <td>Postcode:</td>
            <td>
                <input name="zipcode" type="text" style="width: 400px" placeholder="8345BP"  value="${param.zipcode}"><br>
            </td>
        </tr>
        <tr>
            <td colspan="2">${requestScope.placeError}</td>
        </tr>
        <tr>
            <td>Plaats:</td>
            <td>
                <input name="place" type="text" style="width: 400px" placeholder="Urk"  value="${param.place}"><br>
            </td>
        </tr>
    </table>
    <input name="send" type="submit" value="Registreer"><br>
</form>
<p>Heeft u al een account? Klik dan <a href="/login" style="color:#000">hier</a> om naar de loginpagina te gaan!</p>
<%@include file="/WEB-INF/view/website/footer.jsp" %>