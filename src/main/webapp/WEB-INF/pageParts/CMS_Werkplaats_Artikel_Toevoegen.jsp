<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Artikel gebruik registreren" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="task" value="${requestScope.task}"/>
<c:set var="used" value="${requestScope.used}"/>
<%@ page pageEncoding="UTF-8" %>
<script>
    $(document).ready(function(){
        //Als gebruiker stopt met typen gaan we zoeken
        var searchTimeout;
        var searchBox = document.getElementById('searchArticle');
        searchBox.onkeydown = function () {
            if (searchTimeout != undefined) clearTimeout(searchTimeout);
            searchTimeout = setTimeout(getArticles, 400);
        };
        
        function getArticles() {
            $.getJSON("/service/articles?filter="+searchBox.value, function(articles) {
                var list = "";
                $.each(articles, function(i, article){
                    list += '<option value="'+article.id+'">'+article.name+'</option>';
                });
                $("#article").html(list);
            });
        }
    });
</script>
<form class="form" method="POST" action="./toevoegen" >
<div class="tableWrap content">
    <div class="form-group">
        <label for="taskid">Taak :</label>
        <input type="hidden" name="id" id="taskid" value="${task.id}" />
        <input type="text" disabled="" value="T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${task.id}" />" />
        <small class="error">${requestScope.taskError}</small>
    </div>
    <div class="form-group">
        <label for="taskid"></label>
        <input type="text" id="searchArticle" placeholder="Zoek door artikelen" />
    </div>
    
    <div class="form-group">
        <label for="article">Artikel:</label>
        <select name="article" id="article">
            <c:forEach var="article" items="${requestScope.articles}">
                <option <c:if test="${used.article.id == article.id}">selected=""</c:if> value="${article.id}">${article.name}</option>
            </c:forEach>
        </select>
        <small class="error">${requestScope.articleError}</small>
    </div>
    <div class="form-group">
        <label for="count">Aantal:</label>
        <input type="number" name="count" id="count" value="${used.count}" />
        <small class="error">${requestScope.countError}</small>
    </div>
</div>
<footer class="contentMenu">
    <button name="send" value="Opslaan">Opslaan</button>
    <a class="button" href="/cms/werkplaats" title="">Annuleren</a>
</footer>
</form>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>