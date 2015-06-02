<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Factuur Aanpassen" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form">
<div class="tableWrap content">
    <div class="form-group">
        <label for="klant">Klant:</label>
        
        <select name="klant" id="klant">
            <option>Arnoud de Kip</option>
            <option>Willem Windmolen</option>
            <option selected>Keesie Lomo</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="price">Prijs:</label>
        <input type="number" name="price" id="price" value="200" />
    </div>
    
    <div class="form-group">
        <label for="voldaan">Voldaan:</label>
        <input type="checkbox" name="voldaan" id="voldaan" checked />
    </div>
</div>
<footer class="contentMenu">
    <button type="submit">Factuur bijwerken</button>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>