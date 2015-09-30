<%-- 
    Document   : header
    Created on : 29 sept. 2015, 14:11:19
    Author     : Miguel
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
   </head>
   <body>
      <div class="header">
         <a href="" class="title">Gary</a>
         <div class="container">
            <button class="btn btnHeaderSelected btn-link" type="button">Apps</button>
            <button class="btn btnHeader btn-link" type="button">Account</button>
            <div class="headerRight">
               Logged in as miguel.santamaria@heig-vd.ch
               <button id="btnLogout" class="btn btnLogout btn-danger" type="button">Logout</button>
            </div>
         </div>
      </div>
      <div class="container">
      