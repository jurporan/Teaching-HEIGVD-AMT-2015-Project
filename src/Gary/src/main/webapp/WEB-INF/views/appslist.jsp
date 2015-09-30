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
         <li>${apps.name}, ${apps.description}, ${apps.apiKey}, ${beer.style}</li>
         
         <tr>
            <td>${apps.name}</td>
            <td>${apps.description}</td>
            <td>${apps.apiKey}</td>
            <td>${apps.nbUsers}</td>
         </tr>
      </c:forEach>
   </table>
   
   <table class="appsTable">
      <tr>
         <th>Name</th>
         <th>Description</th>
         <th>Api Key</th>
         <th>Users</th>
         <th></th>
      </tr>
      <tr>
         <td>demo1</td>
         <td>just a test...</td>
         <td>$adi684jdmcosj301#lkxm</td>
         <td>no user</td>
         <td class="appButtons">
            <button id="btnEdit1" class="btn btn-default" type="button">Edit</button>
            <button id="btnStatus1" class="btn btnStatus btn-success" type="button">Enabled</button>
         </td>
      </tr>
      <tr>
         <td>a test app</td>
         <td>This application was...</td>
         <td>$lkdbaécbalbabla#lkxm</td>
         <td>299'225</td>
         <td class="appButtons">
            <button id="btnEdit2" class="btn btn-default" type="button">Edit</button>
            <button id="btnStatus2" class="btn btnStatus btn-danger" type="button">Disabled</button>
         </td>
      </tr>
      <tr>
         <td>my photo app</td>
         <td>A cool app that...</td>
         <td>$jérômemoret#lkxm</td>
         <td>1'110</td>
         <td class="appButtons">
            <button id="btnEdit3" class="btn btn-default" type="button">Edit</button>
            <button id="btnStatus3" class="btn btnStatus btn-success" type="button">Enabled</button>
         </td>
      </tr>
   </table>

<%@include file="includes/footer.jsp" %>