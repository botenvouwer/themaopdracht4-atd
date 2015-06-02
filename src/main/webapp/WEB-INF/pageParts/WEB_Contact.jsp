<%@include file="/WEB-INF/view/website/header.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<h2>Contact</h2>
<div style="float: right; margin-left: 35vw">
    <p>Voor eventuele vragen en/of opmerkingen kunt u ons een bericht sturen.</p>
</div>
<div style="float: left">
    <h3>Contactformulier</h3>
    <% 
    String sent = (String)request.getAttribute("sent");
    if(sent != null && sent.equals("true")){ 
    %>
       <p>Uw bericht is verstuurd en u krijgt hier zo snel mogelijk antwoord op.</p> 
    <% }else{ %>
    <div id="error">&nbsp;
    <% String error = (String)request.getAttribute("error"); 
    String name = "", email = "", message = "";
    if(error != null){
        out.write(error);
        name = (String)request.getAttribute("name");
        email = (String)request.getAttribute("email");
        message = (String)request.getAttribute("message");
    }
    %></div>
    <form method="post" action="/contact">
        <input type="text" name="name" placeholder="Uw naam" value="<% out.write(name); %>">
        <input type="email" name="email" placeholder="Uw email" value="<% out.write(email); %>"><br>
        <textarea placeholder="Bericht" name="message"><% out.write(message); %></textarea><br>
        <input type="submit" name="verstuur" value="Verstuur">
    </form>
    <% } %>
</div>

<%@include file="/WEB-INF/view/website/footer.jsp" %>