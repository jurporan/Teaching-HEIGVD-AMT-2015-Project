<%-- 
    Document   : header2
    Created on : 08-Oct-2015, 22:43:13
    Author     : mel
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
			
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/style.css" rel="stylesheet">
      
        <script src="resources/lib/jquery/jquery.min.js"></script>
        <script src="resources/lib/bootstrap/bootstrap.min.js"></script>
        
    <script type="text/javascript">
        document.getElementById("btnLogin").onclick = function() {
            location.href = "/login";
        };
    </script>
           
    </head>
	
    <body>
        <div class="wrapper">
            <div class="header">
                <%-- <a href="" class="title"><img class="titleImg" src="resources/img/logo.png" />Gary</a> --%>
                
                <div class="container navbar-static">
                    <a href="" id="linkLogo" class="title"><img class="titleImg" src="resources/img/logo.png" />Gary</a>
                    <div>
                        <button id="bntLogin" type="button btn-nav" class="btn pull-right btn-gary" name="btnRegister" value="btnRegister" onClick="location.href='registration';">Create account</button>
                        <button id="btnCreateAccount" type="button btn-nav" class="btn pull-right btn-login btn-gary" name="btnLogin" value="btnLogin" onClick="location.href='login';">Login</button>
                    </div>			  	
                </div>
            </div>
            <div class="container">
                 
                
                
