<%-- 
    Document   : createAccount
    Created on : 08-Oct-2015, 22:38:04
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>
    <form method="POST" action="registration" class="form-vertical" role="form">
        <input type="hidden" name="action" value="login" />
        <div class="form-group">
            <label for="firstname" class="col-md-2 control-label">First name</label>
            <div class="col-md-10">
                <input name="firstname" type="text" class="form-control" placeholder="first name" value ="firstname" required />
            </div>
        </div>
        
        <div class="form-group">
            <label for="lastname" class="col-md-2 control-label">Last name</label>
            <div class="col-md-10">
                <input name="lastname" type="text" class="form-control" placeholder="last name" value ="lastname" required />
            </div>
        </div>     
        <div class="form-group">
            <label for="email" class="col-md-2 control-label">E-mail</label>
            <div class="col-md-10">
                <input name="email" type="email" class="form-control" placeholder="e-mail" value ="email" required />
            </div>
        </div>    
        <div class="form-group">
            <label for="pass1" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input name="pass1" type="password" class="form-control" placeholder="password" value ="password" required />
            </div>
        </div>
        <div class="form-group">
            <label for="pass2" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input name="pass2" type="password" class="form-control" placeholder="password" value ="passwordConfirmation"required />
            </div>
        </div>
    </div> 

    <div class="container pourcent">
        <button type="submit" class="btn btn-default  pull-right">Create account</button>
    </div>  
<%@include file="includes/footer.jsp" %>