<%@page import="domain.Delivery.Status"%>
<%@page import="domain.Delivery"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Bestellingen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Artikel</th>
            <th>Aantal</th>
            <th>Status</th>
            <th>Acties</th>
        </tr>
        
        <% List<Delivery> deliverys = (List<Delivery>) request.getAttribute("deliverys"); %>
        <% for(Delivery d : deliverys) { %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= d.getArticleName() %></td>
            <td><%= d.getCount()%></td>
            <td>
                <% 
                    if (d.getStatus().equals(Status.STANDAARD)) {
                        out.println("In Bestelling");
                    } else if (d.getStatus().equals(Status.GEANNULEERD)) {
                        out.println("Geannuleerd");
                    } else if (d.getStatus().equals(Status.GELEVERD)) {
                        out.println("Geleverd");
                    }
                %>
            </td>
            <td class="right">
                <button onclick="location.href='/cms/bestellingen?status=1&id=<%= d.getId() %>'">Geleverd</button>
                <button onclick="location.href='/cms/bestellingen?status=2&id=<%= d.getId() %>'">Niet Geleverd</button>
                <button onclick="location.href='/cms/bestellingen?status=3&id=<%= d.getId() %>'">Annuleren</button>
            </td>
        </tr>
        <% } %>
    </table>
</div>

<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="bestellingen/aanmaken" title="">Nieuwe bestelling</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Toon afgeronde</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>