<%-- 
    Document   : editapp
    Created on : 6 oct. 2015, 10:07:33
    Author     : Miguel
--%>

<%@include file="includes/header.jsp" %>

   <script type="text/javascript">
   <!--
      $(document).ready(function() {
         $("#btnState").click(function() {
            var enableValue = "Enabled";
            var disableValue = "Disabled";
            
            if($("#inputState").val() === enableValue)
            {
               $("#btnState")
                       .attr("class", "btn btnStatus btn-danger")
                       .text(disableValue);
               $("#inputState").attr("value", disableValue);
            }
            else
            {
               $("#btnState")
                       .attr("class", "btn btnS</tatus btn-success")
                       .text(enableValue);
               $("#inputState").attr("value", enableValue);
            }
         });
      });
   -->
   </script>

   <h1>${pageTitle}</h1>
   
   <br/>
   
   <form method="POST" action="app?action=add" role="form" class="form-horizontal">
      <div class="form-group">
         <label class="control-label col-sm-2" for="name">Name *</label>
         <div class="col-sm-10">
            <input id="appName" type="text" name="name" class="form-control" placeholder="Enter name" required="required" />
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="description">Description</label>
         <div class="col-sm-10">
            <textarea id="descritpionName" name="description" class="form-control" placeholder="Enter description"></textarea>
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="btnState">State *</label>
         <div class="col-sm-10">
            <input id="inputState" type="hidden" name="state" value="Enabled" />
            <button id="btnState" name="btnState" class="btn btnStatus btn-success" type="button">Enabled</button>
            
            <span class="span-btn-form">
               <span class="mandatoryFields">* Mandatory fields</span>
               <button name="formAction" id="btnCancel" class="btn btn-gary btn-form" type="button" value="cancel" onClick="location.href='appslist';">Cancel</button>
               <button name="formAction" id="btnCreate" class="btn btn-gary btn-form" type="submit" value="create">Create</button>
            </span>
         </div>
      </div>
         
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