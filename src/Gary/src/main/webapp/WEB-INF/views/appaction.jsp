<%-- 
    Document   : editapp
    Created on : 6 oct. 2015, 10:07:33
    Author     : Miguel Santamaria
    Goal       : Allows the connected user to add a new app or to edit one of 
                 his apps.
--%>

<%@include file="includes/header.jsp" %>

   <script type="text/javascript">
   <!--
      $(document).ready(function() {
         // When the user clicks on the status button, the color and the text change.
         $("#btnStatus").click(function() {
            var enableValue = "Enabled";
            var disableValue = "Disabled";
            
            if($("#inputState").val() === enableValue)
            {
               $("#btnStatus")
                       .attr("class", "btn btnStatus btn-danger")
                       .text(disableValue);
               $("#inputState").attr("value", disableValue);
            }
            else
            {
               $("#btnStatus")
                       .attr("class", "btn btnStatus btn-success")
                       .text(enableValue);
               $("#inputState").attr("value", enableValue);
            }
         });
      });
   -->
   </script>
   
   <img src="resources/img/web.png" style="left: 0; position: absolute; top: 60px; width: 100px;" />

   <h1>${pageTitle}</h1>
   
   <br/>
   
   <!-- Set the form action and the fields' values, depending on if the user is currently adding or editind an app. -->
   <form method="POST" action="${not empty app ? 'app?action=edit&app='.concat(app.id) : 'app?action=add'}" role="form" class="form-horizontal">
      <div class="form-group">
         <label class="control-label col-sm-2" for="name">Name *</label>
         <div class="col-sm-10">
            <input id="appName" type="text" name="name" class="form-control" placeholder="Enter name" required="required" value="${not empty app ? app.name : ''}" />
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="description">Description</label>
         <div class="col-sm-10">
            <textarea id="appDescription" name="description" class="form-control" placeholder="Enter description">${not empty app ? app.description : ''}</textarea>
         </div>
      </div>
      
      <!-- Show more fields if the user is editing the app. -->
      <c:if test="${not empty app}">
        <div class="form-group">
            <label class="control-label col-sm-2" for="apiKey">API Key *</label>
            <div class="col-sm-10">
               <p name="apiKey" class="form-control-static">${app.apiKey}</p>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="nbUsers"># Users *</label>
            <div class="col-sm-10">
               <p name="nbUsers" class="form-control-static">${app.numberOfUsers}</p>
            </div>
        </div>
      </c:if>
         
      <div class="form-group">
         <label class="control-label col-sm-2" for="btnStatus">State *</label>
         <div class="col-sm-10">
            <!-- The status button rendering is different, depending on the status itself.-->
            <!-- For the adding page, it is enabled by default. -->
            <c:choose>
               <c:when test="${empty app or app.active}">
                  <input id="inputState" type="hidden" name="state" value="Enabled" />
                  <button id="btnStatus" name="btnStatus" class="btn btnStatus btn-success" type="button">Enabled</button>
               </c:when>
               <c:otherwise>
                  <input id="inputState" type="hidden" name="state" value="Disabled" />
                  <button id="btnStatus" name="btnStatus" class="btn btnStatus btn-danger" type="button">Disabled</button>
               </c:otherwise>
            </c:choose>
                  
            <span class="span-btn-form">
               <span class="mandatoryFields">* Mandatory fields</span>
               <button name="formAction" id="btnCancel" class="btn btn-gary btn-form" type="button" value="cancel" onClick="location.href='appslist';">Cancel</button>
               <button name="formAction" id="btnAction" class="btn btn-gary btn-form" type="submit" value="${not empty app ? 'edit' : 'create'}">${not empty app ? "Edit" : "Create"}</button>
            </span>
         </div>
      </div>
      
      <!-- If there is an error, we show it. -->
      <c:if test="${not empty error}">
         <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
               <div class="panel panel-danger">
                  <div class="panel-heading">
                     ${error}
                  </div>
               </div>
            </div>
         </div>
      </c:if>
   </form>

<%@include file="includes/footer.jsp" %>