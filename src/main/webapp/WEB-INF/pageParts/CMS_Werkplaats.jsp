<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_NL"/>
<c:set var="tasks" value="${requestScope.tasks}" ></c:set>
<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="${(param.toon == 'aanvragen' ? 'Openstaande aanvragen' : (param.plannedFor == null ? 'Taken voor vandaag' : 'Taken voor '.concat(param.plannedFor)))}" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<script> 
    $(document).ready(function(){
        
        $(document).on('click', '#dateNavBtn', function(){
            var date = $('#dateNav').val();
            window.location.href = '/cms/werkplaats?plannedFor='+date;
        });
            
        $(document).on('click', '.addArticleBTN', function(){
            var btn = $(this);
            var id = btn.attr('taskid');
            window.location.href = '/cms/werkplaats/artikel/toevoegen?id='+id;
        });
        
        //test
        $(document).on('click', '.removeArticleBTN', function(){
            var btn = $(this);
            var taskid = btn.attr('taskid');
            var articleid = btn.attr('articleid');

            $.get("/cms/werkplaats/artikel/verwijderen?taskid="+taskid+"&articleid="+articleid);
            $('#ua_'+articleid).remove();
        });
        
    });
</script>
<div class="werkplaats">
    <c:forEach var="task" items="${tasks}">
        <div id="task_${task.id}" class="${task.status == 'FINISHED' ? 'done' : 'busy'}">
            <div class="infoCard">
                <div>T<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${task.id}" /></div>
                <div class="repairType">
                    <c:choose>
                        <c:when test="${task.type == 'APK'}">
                            <img src="/img/apkTask.png" title="APK (Algemene Periodieke Keuring)" />
                        </c:when>
                            
                        <c:when test="${task.type == 'REPAIR'}">
                            <img src="/img/repairTask.png" title="APK (Algemene Periodieke Keuring)" />
                        </c:when>
                    </c:choose>
                </div>
                <div>${task.status}</div>
                <div>Uren: ${task.hours}</div>
                <div>${task.car.brand} ${task.car.model}</div>
                <div>${task.car.licensePlate}</div>
                <div><fmt:formatDate value="${task.plannedFor.time}" timeStyle="short" type="date" /></div>
                <c:choose>
                    <c:when test="${task.status == 'REQUEST' && sessionScope.user.role == 'BOSS'}">
                        <input type="button" value="Inplannen" onclick="window.location.href='/cms/werkplaats/plannen?id=${task.id}'" />
                    </c:when>
                    <c:when test="${task.status == 'PLANNED'}">
                        <input type="button" value="Uitboeken" onclick="window.location.href='/cms/werkplaats/uitboeken?id=${task.id}'" />
                    </c:when>
                </c:choose>
            </div>
            <div class="noteCard">
                <div>
                    <b>Klant: <i><a>${task.customer.name}</a></i></b>
                    <p>${task.customerNote}</p>
                </div>
                <div>
                    <b>Monteur: <i><a>${task.mechanic.name}</a></i></b>
                    <p>${task.mechanicNote}</p>
                </div>
            </div>
            <div class="articleCard">
                <table class="dataTable">
                    <tr>
                        <th>Gebruikt</th>
                        <th>Art num</th>
                        <th>Artikel</th>
                        <th></th>
                    </tr>
                    <c:forEach var="used" items="${task.usedArticles}">
                        <tr id="ua_${used.id}">
                            <td class="center"><input disabled="" type="number" name="test" value="${used.count}" /></td>
                            <td>A<fmt:formatNumber minIntegerDigits="8" groupingUsed="" value="${used.article.id}" /></td>
                            <td>${used.article.name}</td>
                            <td class="right"><c:if test="${task.status != 'FINISHED'}"><a class="removeArticleBTN" taskid="${task.id}" articleid="${used.id}" href="" >Verwijderen</a></c:if></td>
                        </tr>    
                    </c:forEach>
                    <c:if test="${task.status != 'FINISHED' && task.status != 'REQUEST'}">
                        <tr>
                            <td colspan="4"><input type="button" class="addArticleBTN" value="Gebruikt artikel toevoegen of wijzigen" taskid="${task.id}" name=""></td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </c:forEach>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <c:if test="${sessionScope.user.role == 'BOSS'}">
            <li class="button">
                <a href="werkplaats/toevoegen" title="">Taak Toevoegen</a>
            </li>
            <c:choose>
                <c:when test="${param.toon == 'aanvragen'}">
                    <li class="button">
                        <a href="werkplaats" title="">Toon alles</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="button">
                        <a href="werkplaats?toon=aanvragen" title="">Toon aanvragen</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:if>
        <li>
            <input style=" min-width: 127px; padding: 1px 7px; " type="date" id="dateNav" class="button">
        </li>
        <li>
            <button id="dateNavBtn">Ga naar datum</button>
        </li>
    </ul>
    
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>