<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Gebruikers" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Naam</th>
            <th>Email</th>
            <th>Type</th>
            <th>Acties</th>
        </tr>
        <tr>
            <td>G000001</td>
            <td>Arnoud de Kip</td>
            <td>arni@hotmail.com</td>
            <td>Monteur</td>
            <td class="right">
                <button onclick="window.location.href='gebruiker/aanpassen'">Aanpassen</button>
            </td>
        </tr>
        <tr>
            <td>G000002</td>
            <td>Willem windmolen</td>
            <td>wwmolen@hotmail.com</td>
            <td>Klant</td>
            <td class="right">
                <button onclick="window.location.href='gebruiker/aanpassen'">Aanpassen</button>
            </td>
        </tr>
        <tr>
            <td>G000003</td>
            <td>Keesie Lomo</td>
            <td>k.lomo@hotmail.com</td>
            <td>Chef</td>
            <td class="right">
                <button onclick="window.location.href='gebruiker/aanpassen'">Aanpassen</button>
            </td>
        </tr>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="gebruiker/aanmaken" title="">Gebruiker aanmaken</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>