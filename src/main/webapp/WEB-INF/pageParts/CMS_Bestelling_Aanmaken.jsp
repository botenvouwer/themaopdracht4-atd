<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Bestelling Aanmaken" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<form class="form">
<div class="tableWrap content">
    <div class="form-group">
        <label for="article">Artikel:</label>
        
        <select name="article" id="article">
            <option>Wieldopje</option>
            <option>Ramenwisser</option>
            <option>Windscherm</option>
        </select>
    </div>
    
    <div class="form-group">
        <label for="count">Aantal</label>
        <input type="number" name="count" id="price" />
    </div>
    
    <div class="form-group">
        <label for="status">Uitgeboekt:</label>
        
        <select name="article" id="article">
            <option>In Bestelling</option>
            <option>Niet Geleverd</option>
            <option>Geleverd</option>
        </select>
    </div>
</div>
<footer class="contentMenu">
    <button type="submit">Bestelling aanmaken</button>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>