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
            <td><a id="linkUsersApp" href="userslist?app=${apps.id}&page=1">${apps.numberOfUsers}</a></td>
            <td class="appButtons">
               <button id="btnEdit${apps.id}" class="btn btn-default" type="submit" onClick="location.href='app?action=edit&app=${apps.id}';">Edit</button>
               <c:choose>
                  <c:when test="${apps.active}">
                     <button id="btnStatus${apps.id}" class="btn btnStatus btn-success" type="button" disabled="disabled">Enabled</button>
                  </c:when>
                  <c:otherwise>
                     <button id="btnStatus${apps.id}" class="btn btnStatus btn-danger" type="button" disabled="disabled">Disabled</button>
                  </c:otherwise>
               </c:choose>
            </td>
         </tr>
      </c:forEach>
   </table>
   <br/><br/><br/><br/>
    <table>
        <tr>
            <td>Page ${pageNumber}/${numberOfPages}</td>
            <td><a href="appslist?page=1&per_page=${itemsPerPage}">First page</a></td>
            
            <td>
                <c:choose>
                    <c:when test="${pageNumber > 1}">
                        <a href="appslist?page=${pageNumber - 1}&per_page=${itemsPerPage}">Previous page</a>
                    </c:when>
                    <c:otherwise>
                        Previous page
                    </c:otherwise>
                </c:choose>
            </td>
                    
            <td>
                <c:choose>
                    <c:when test="${pageNumber < numberOfPages}">
                        <a href="appslist?page=${pageNumber + 1}&per_page=${itemsPerPage}">Next page</a>
                    </c:when>
                    <c:otherwise>
                        Next page
                    </c:otherwise>
                </c:choose>
            </td>
                    
            <td><a href="appslist?page=${numberOfPages}&per_page=${itemsPerPage}">Last page</a></td>
        </tr>
    </table>

<%@include file="includes/footer.jsp" %>