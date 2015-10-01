<%-- 
    Document   : applist
    Created on : 25 sept. 2015, 10:03:50
    Author     : Miguel
--%>

<%@include file="includes/header.jsp" %>

   <h1 class="appsTitle">Your apps</h1>
   <button id="btnNewApp" class="btn btnRegisterNew btn-danger" type="button">Register new app</button>

   <table class="appsTable">
      <tr>
         <th>Name</th>
         <th>Description</th>
         <th>Api Key</th>
         <th>Users</th>
         <th></th>
      </tr>
      <c:forEach items="${apps}" var="apps">
         <tr>
            <td>${apps.name}</td>
            <td>${apps.description}</td>
            <td>${apps.apiKey}</td>
            <td>${apps.numberOfUsers}</td>
            <td class="appButtons">
            <button id="btnEdit1" class="btn btn-default" type="button">Edit</button>
            <button id="btnStatus1" class="btn btnStatus btn-success" type="button">Enabled</button>
         </td>
         </tr>
      </c:forEach>
   </table>

<%@include file="includes/footer.jsp" %>