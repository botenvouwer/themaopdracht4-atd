<%@page import="java.text.SimpleDateFormat"%>
<%@page import="domain.Delivery.Status"%>
<%@page import="domain.Delivery"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Bestellingen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="tableWrap">
    <table class="dataTable">
        <tr>
            <th>Nummer</th>
            <th>Datum</th>
            <th>Artikel</th>
            <th class="center">Aantal</th>
            <th>Status</th>
            <th>Acties</th>
        </tr>
        
        <% List<Delivery> deliverys = (List<Delivery>) request.getAttribute("deliverys"); %>
        <% for(Delivery d : deliverys) { %>
        <tr>
            <td><%= d.getId() %></td>
            <td><%= String.format("%1$TD", d.getDate()) %></td>
            <td><%= d.getArticleName() %></td>
            <td class="center"><%= d.getCount()%></td>
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
            <a href="bestellingen" title="">Toon Alles</a>
        </li>
        <li class="button">
            <a href="bestellingen?toon=geleverd" title="">Toon Geleverde</a>
        </li>
        <li class="button">
            <a href="bestellingen?toon=standaard" title="">Toon In Bestelling</a>
        </li>
        <li class="button">
            <a href="bestellingen?toon=geannuleerd" title="">Toon Geannuleerde</a>
        </li>
    </ul>
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>