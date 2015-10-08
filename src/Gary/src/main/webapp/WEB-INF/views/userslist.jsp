<%-- 
    Document   : userlist
    Created on : 6 oct. 2015, 09:13:51
    Author     : Miguel
--%>

<%@include file="includes/header.jsp" %>

   <h1>List of users for "my photo app"</h1>
   
   <table class="usersTable">
      <tr>
         <th>User id</th>
         <th>Creation date</th>
      </tr>
      <tr>
         <td>UK193GDUW7</td>
         <td>04.10.2015</td>
      </tr>
      <tr>
         <td>LOIWMXB891</td>
         <td>06.10.2015</td>
      </tr>
   </table>
   
   <br/>
   
   <table>
      <tr>
         <td>Page 12/225</td>
         <td><a href="userslist?app=2&page=1">First page</a></td>
         <td>Previous page</td>
         <td>Next page</td>
         <td>Last page</td>
      </tr>
   </table>
   
<%@include file="includes/footer.jsp" %>