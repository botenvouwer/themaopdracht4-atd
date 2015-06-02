<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="/css/ATDcms.css">
        <link type="text/css" rel="stylesheet" href="/css/main.css">
        <title>Auto Totaal Dienst</title>
    </head>
    <body>
        <div id="main" class="main">
            <header id="mainHeader" class="main">
                <div id="logo">
                    <img src="/img/logo.png">
                </div>
                <div id="title">${param.title}</div>
                <div id="user">${sessionScope.user.name}</div>
            </header>
            <div id="masterdiv">
                <div id="sideBar" class="nonselect">
                    <div id="menuWrap">
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
                        </ul>
                    </div>
                    <div id="sideLogo">
                        <img src="/img/blogo.png">
                    </div>
                </div>
                <div id="mainContent">