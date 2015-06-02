<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8"%>
<%
String type = "";
if(request.getParameter("type") != null){
   type = (String)request.getParameter("type"); 
}
%>
<script>
    function setDatum(){
        if(document.getElementById("vertrek").value == "" || document.getElementById("vertrek").value < document.getElementById("arriveer").value){
            document.getElementById("vertrek").value = document.getElementById("arriveer").value;
        }
    }
</script>
<h1>Afspraak maken</h1>
<div style="text-align: center">
    <a href="?type=parkeerplaats"><input type="button" value="Parkeerplaats reserveren" <% if(type.equals("parkeerplaats")){ %> disabled="true" <% } %>></a>
    <a href="?type=werkplaats"><input type="button" value="Werkplaats afspraak maken" <% if(type.equals("werkplaats")){ %> disabled="true" <% } %>></a>
</div>

<% if(type.equals("parkeerplaats")){ %>
<div style="text-align: center; padding-top: 30px">
    <table>
        <tr><td>Arriveer datum</td><td><input type="date" id="arriveer" name="arriveer" onchange="setDatum()"></td></tr>
        <tr><td>Vertrek datum</td><td><input type="date" id="vertrek" name="vertrek"></td></tr>
        <tr><td colspan="2"><input type="submit" value="Reserveer"></td></tr>
    </table>
</div>
<% } %>

<% if(type.equals("werkplaats")){ %>
<div style="padding-top: 30px">
    <table>
        <tr><td>Arriveer datum</td><td><input type="date" name="arriveer"></td></tr>
        <tr><td>Soort afspraak</td><td><input type="radio" name="type" id="apk" value="apk"><label for="apk">APK</label><br><input type="radio" name="type" id="reparatie" value="reparatie"><label for="reparatie">Reparatie</label></td></tr>
        <tr><td>Opmerking</td><td style="font-size: 10px">Hoe meer info u hier opgeeft, hoe beter en sneller wij u kunnen helpen.</td></tr>
        <tr><td colspan="2"><textarea></textarea></td></tr>
        <tr><td colspan="2" style="text-align: center"><input type="submit" value="Maak afspraak"></td></tr>
    </table>
</div>
<% } %>
<%@include file="/WEB-INF/view/website/footer.jsp" %>