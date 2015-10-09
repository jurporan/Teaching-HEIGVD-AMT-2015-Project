<%-- 
    Document   : editapp
    Created on : 6 oct. 2015, 10:07:33
    Author     : Miguel
--%>

<%@include file="includes/header.jsp" %>

   <h1>${pageTitle}</h1>
   
   <br/>
   
   <form method="POST" action="app" role="form" class="form-horizontal">
      <div class="form-group">
         <label class="control-label col-sm-2" for="name">Name</label>
         <div class="col-sm-10">
            <input type="text" name="name" class="form-control" placeholder="Enter name" required />
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="description">Description</label>
         <div class="col-sm-10">
            <textarea name="description" class="form-control" placeholder="Enter description"></textarea>
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="apiKey">API Key</label>
         <div class="col-sm-10">
            <p name="apiKey" class="form-control-static">klsmxascj93u8cha</p>
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="nbUsers"># Users</label>
         <div class="col-sm-10">
            <p name="nbUsers" class="form-control-static">233984</p>
         </div>
      </div>
      
      <div class="form-group">
         <label class="control-label col-sm-2" for="state">State</label>
         <div class="col-sm-10">
            <button class="btn btnStatus btn-success" name="state" type="button">Enabled</button>
         </div>
      </div>
      
      <div class="form-group">
         <div class="col-sm-offset-2 col-sm-10">
            <button id="bSignIn" class="btn btn-lg btn-primary" type="submit">Cancel</button>
            <button id="bSignIn" class="btn btn-lg btn-primary" type="submit">Update</button>
         </div>
      </div>
   </form>

<%@include file="includes/footer.jsp" %>