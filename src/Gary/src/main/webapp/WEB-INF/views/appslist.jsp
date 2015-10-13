<%-- 
    Document   : applist
    Created on : 25 sept. 2015, 10:03:50
    Author     : Miguel
--%>

<%@include file="includes/header.jsp" %>

   <h1 class="pageTitle">${pageTitle}</h1>
   <button id="btnNewApp" class="btn btnRegisterNew btn-gary" type="button" onClick="location.href='app?action=add';">Register new app</button>
   
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
            <td><a href="userslist?app=${apps.id}&page=1">${apps.numberOfUsers}</a></td>
            <td class="appButtons">
               <button id="btnEdit${apps.id}" class="btn btn-default" type="submit" onClick="location.href='app?action=edit&app=${apps.id}';">Edit</button>
               <c:choose>
                  <c:when test="${apps.active}">
                     <button id="btnStatus${apps.id}" class="btn btnStatus btn-success" type="button">Enabled</button>
                  </c:when>
                  <c:otherwise>
                     <button id="btnStatus${apps.id}" class="btn btnStatus btn-danger" type="button">Disabled</button>
                  </c:otherwise>
               </c:choose>
            </td>
         </tr>
      </c:forEach>
   </table>

<%@include file="includes/footer.jsp" %>