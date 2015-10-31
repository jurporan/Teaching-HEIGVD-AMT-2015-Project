<%-- 
    Document   : applist
    Created on : 25 sept. 2015, 10:03:50
    Author     : Miguel Santamaria
    Goal       : Show the connected user's apps, based on a pagination system.
--%>

<%@include file="includes/header.jsp" %>

   <script type="text/javascript">
   <!--
      /**
      * This little guy achieves an AJAX request to the AppActionServlet with POST
      * parameters, to simulate a app's edition, all of this when a click on an app's
      * status button occurs.
      * In this case we only want to edit the app's status.
      */
      function sendStatusChangeWithAjax(buttonId, action)
      {
          // The AJAX POST request's parameters.
          var dataString = "formAction=edit&state=" + 
                            (action === "enable" ? "Enabled" : "Disabled") + 
                            "&ajax=true"
          
          // Make an AJAX POST request to the AppActionServlet to update the app's status.
          // We expect to receive back JSON as a response.
          $.ajax({
            type: "POST",
            url:  "app?action=edit&app=" + buttonId,
            data: dataString,
            dataType: 'json',
            // Occurs when the AJAX request successfully executed.
            success: function (data)
            {
                // In case of success, we change the concerned button's rendering,
                // depending on the action of the user.
                 if(data.success)
                 {
                    if(action === "disable")
                    {
                       $("#btnStatus" + buttonId)
                               .attr("class", "btn btnStatus btn-danger")
                               .attr("onClick", "sendStatusChangeWithAjax(" + buttonId + ", 'enable');")
                               .text("Disabled");
                    }
                    else
                    {
                       $("#btnStatus" + buttonId)
                               .attr("class", "btn btnStatus btn-success")
                               .attr("onClick", "sendStatusChangeWithAjax(" + buttonId + ", 'disable');")
                               .text("Enabled");
                    }
                }
                // If there was an error during the request, we show it to the user.
                else
                {
                    alert("An error occured, please retry.");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("An error occured, please retry.");
            },
            // Capture the request before it was sent to server so we can disable the
            // status button during the request.
            beforeSend: function(jqXHR, settings) {
                $("#btnStatus" + buttonId).attr("disabled", true);
            },

            // This is called after the response or error functions are finsihed
            // so that we can enable the status button.
            complete: function(jqXHR, textStatus) {
                $("#btnStatus" + buttonId).attr("disabled", false);
            }
          });
      }
   -->
   </script>

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
      <!-- Display in a table each received user's app. -->
      <c:forEach items="${apps}" var="app">
         <tr>
            <td id="nameApp${app.id}">${app.name}</td>
            <td id="descriptionApp${app.id}">${app.description}</td>
            <td>${app.apiKey}</td>
            <td><a id="linkUsersApp" href="userslist?app=${app.id}&page=1">${app.numberOfUsers}</a></td>
            <td class="appButtons">
               <button id="btnEdit${app.id}" class="btn btn-default" type="submit" onClick="location.href='app?action=edit&app=${app.id}';">Edit</button>
               <!-- The status' button rendering is different depending on the status itself. -->
               <c:choose>
                  <c:when test="${app.active}">
                     <button name="btnStatus" id="btnStatus${app.id}" class="btn btnStatus btn-success" type="button" onClick="sendStatusChangeWithAjax(${app.id}, 'disable');">Enabled</button>
                  </c:when>
                  <c:otherwise>
                     <button name="btnStatus" id="btnStatus${app.id}" class="btn btnStatus btn-danger" type="button" onClick="sendStatusChangeWithAjax(${app.id}, 'enable');">Disabled</button>
                  </c:otherwise>
               </c:choose>
            </td>
         </tr>
      </c:forEach>
   </table>
   
    <!-- This table show the apps' pagination system. -->
    <table class="paginationTable">
        <tr>
            <td>Page ${pageNumber}/${numberOfPages}</td>
            <td><a href="appslist?page=1&per_page=${itemsPerPage}">First page</a></td>
            
            <td>
                <!-- The "Previous page" link is enabled or not, depending on the current page's number. -->
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
                <!-- The "Next page" link is enabled or not, depending on the current page's number. -->
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