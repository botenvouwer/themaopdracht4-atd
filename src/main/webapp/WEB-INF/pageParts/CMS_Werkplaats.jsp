<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="tasks" value="${requestScope.tasks}" ></c:set>

<jsp:include page="/WEB-INF/view/cms/header.jsp">
    <jsp:param name="title" value="Taken voor vandaag" />
</jsp:include>
<%@ page pageEncoding="UTF-8" %>
<div class="werkplaats">
    <c:forEach var="task" items="${tasks}">
        <div class="done">
            <div class="infoCard">
                <div>ID ${task.id}</div>
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
                <div>Uren: ${task.hours}</div>
                <div>${task.car.brand} ${task.car.model}</div>
                <div>${task.car.licensePlate}</div>
                <div><fmt:formatDate pattern="dd-MM-yyyy" value="${task.plannedFor}" /></div>
                <input type="button" value="Uitboeken" />
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
                    <tr>
                        <td class="center"><input type="number" name="test" value="2" /></td>
                        <td>A000234</td>
                        <td>Motor klep a564 type 4</td>
                        <td class="right"><input type="button" value="verwijderen" /></td>
                    </tr>
                    <tr>
                        <td class="center"><input type="number" name="test" value="1" /></td>
                        <td>A000139</td>
                        <td>Motor smeer oli</td>
                        <td class="right"><input type="button" value="verwijderen" /></td>
                    </tr>
                    <tr>
                        <td colspan="4"><input type="button" value="Artikel toevoegen"></td>
                    </tr>
                </table>
            </div>
        </div>
    </c:forEach>
</div>
<footer class="contentMenu">
    <ul class="menu">
        <li class="button">
            <a href="werkplaats/toevoegen" title="">Taak Toevoegen</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Dag terug</a>
        </li>
        <li class="button">
            <a href="/cms/" title="">Dag verder</a>
        </li>
    </ul>
    <input type="date" name="goto" class="button">
</footer>
<%@include file="/WEB-INF/view/cms/footer.jsp" %>