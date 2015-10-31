<%-- 
    Document   : header
    Created on : 29 sept. 2015, 14:11:19
    Author     : Miguel Santamaria
    Goal       : The header of pages that the connected user can see.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <base href="${pageContext.request.contextPath}/">
      <title>${pageTitle}</title>
      <link rel="shortcut icon" href="resources/img/logo.png">
      
      <link href="resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="resources/css/style.css" rel="stylesheet">
      
      <script src="resources/lib/jquery/jquery.min.js"></script>
      <script src="resources/lib/bootstrap/bootstrap.min.js"></script>
   </head>
   <body>
      <div class="header">
         <div class="container navbar-static">
            <a href="" id="linkLogo" class="title"><img class="titleImg" src="resources/img/logo.png" />Gary</a>
            
            <c:choose>
                <c:when test="${not empty email}">
                    <c:choose>
                        <c:when test="${selectedHeaderElem == 'apps'}">                
                            <button id="btnHeaderApps" class="btn btnHeaderSelected btn-link" type="button" onClick="location.href='appslist';">Apps</button>
                            <button id="btnHeaderAccount" class="btn btnHeader btn-link" type="button" onClick="location.href='editaccount';">Account</button>
                        </c:when>
                        <c:otherwise>
                            <button id="btnHeaderApps" class="btn btnHeader btn-link" type="button" onClick="location.href='appslist';">Apps</button>
                            <button id="btnHeaderAccount" class="btn btnHeaderSelected btn-link" type="button" onClick="location.href='editaccount';">Account</button>
                        </c:otherwise>
                    </c:choose>
                    <div class="headerRight">
                       <span class="loginInformation">Logged in as ${email}</span>
                       <a id="btnLogout" class="btn btnLogout btn-gary" href="login?action=logout">Logout</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <button id="bntLogin" type="button btn-nav" class="btn pull-right btn-gary" name="btnRegister" value="btnRegister" onClick="location.href='registration';">Create account</button>
                        <button id="btnCreateAccount" type="button btn-nav" class="btn pull-right btn-login btn-gary" name="btnLogin" value="btnLogin" onClick="location.href='login';">Login</button>
                    </div>
                </c:otherwise>
            </c:choose>
         </div>
      </div>
      <div class="container">
         <br/>
      