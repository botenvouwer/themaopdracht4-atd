<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Facturatie" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Klant</th>
            <th>Totaal prijs</th>
            <th>Verzonden</th>
            <th>Voldaan</th>
            <th>Datum</th>
            <th>Acties</th>
        </tr>
        <tr>
            <td>F000001</td>
            <td><a href="/cms">Kees Appel</a></td>
            <td>€200,-</td>
            <td class="center">20-04-2014</td>
            <td class="center">Nee</td>
            <td class="center">14-04-2014</td>
            <td class="right">
                <button onclick="window.location.href='factuur/aanpassen'">Aanpassen</button>
                <button>Uitboeken</button>
            </td>
        </tr>
        <tr>
            <td>F000002</td>
            <td><a href="/cms">Kees Appel</a></td>
            <td>€200,-</td>
            <td class="center">20-04-2014</td>
            <td class="center">Nee</td>
            <td class="center">14-04-2014</td>
            <td class="right">
                <button onclick="window.location.href='factuur/aanpassen'">Aanpassen</button>
                <button>Uitboeken</button>
            </td>
        </tr>
        <tr>
            <td>F000003</td>
            <td><a href="/cms">Kees Appel</a></td>
            <td>€200,-</td>
            <td class="center">20-04-2014</td>
            <td class="center">Nee</td>
            <td class="center">14-04-2014</td>
            <td class="right">
                <button onclick="window.location.href='factuur/aanpassen'">Aanpassen</button>
                <button>Uitboeken</button>
            </td>
        </tr>
        <tr>
            <td>F000004</td>
            <td><a href="/cms">Kees Appel</a></td>
            <td>€200,-</td>
            <td class="center">20-04-2014</td>
            <td class="center">Nee</td>
            <td class="center">14-04-2014</td>
            <td class="right">
                <button onclick="window.location.href='factuur/aanpassen'">Aanpassen</button>
                <button>Uitboeken</button>
            </td>
        </tr>
    </table>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="factuur/aanmaken" title="">Factuur toevoegen</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Alles verzenden</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Toon openstaand</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>