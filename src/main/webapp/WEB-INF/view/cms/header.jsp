<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="/css/ATDcms.css">
        <link type="text/css" rel="stylesheet" href="/css/main.css">
        <script src="/javascript/jquery-2.1.4.min.js" ></script>
        <title>Auto Totaal Dienst</title>
    </head>
    <body>
        <div id="main" class="main">
            <header id="mainHeader" class="main">
                <div id="logo">
                    <img src="/img/logo.png">
                </div>
                <div id="title">${param.title}</div>
                <div id="user">${sessionScope.user.role == 'BOSS' ? 'Chef' : 'Werknemer'}: ${sessionScope.user.name}</div>
            </header>
            <div id="masterdiv">
                <div id="sideBar" class="nonselect">
                    <div id="menuWrap">
                        <c:choose>
                            <c:when test="${user.role == 'BOSS'}">
                                <ul id="mainMenu" class="hmenu">
                                    <li class="button">
                                        <a href="/cms" title="">CMS</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/voorraad" title="">Voorraad</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/bestellingen" title="">Bestellingen</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/werkplaats" title="">Werkplaats</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/factuur" title="">Facturatie</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/gebruiker" title="">Gebruikers</a>
                                    </li>
                                    <li class="button">
                                        <a href="/uitloggen" title="">Uitloggen</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:when test="${user.role == 'EMPLOYEE'}">
                                 <ul id="mainMenu" class="hmenu">
                                    <li class="button">
                                        <a href="/cms" title="">CMS</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/voorraad" title="">Voorraad</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/bestellingen" title="">Bestellingen</a>
                                    </li>
                                    <li class="button">
                                        <a href="/cms/werkplaats" title="">Werkplaats</a>
                                    </li>
                                    <li class="button">
                                        <a href="/uitloggen" title="">Uitloggen</a>
                                    </li>
                                </ul>
                            </c:when>

                            <c:otherwise>
                                <i>Onbekend</i>
                            </c:otherwise>
                      </c:choose>
                    </div>
                    <div id="sideLogo">
                        <img src="/img/blogo.png">
                    </div>
                </div>
                <div id="mainContent">