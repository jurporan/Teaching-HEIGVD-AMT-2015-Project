<%-- 
    Document   : createAccount
    Created on : 08-Oct-2015, 22:38:04
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>
    <form method="POST" action="registration" class="form-vertical" role="form">
        <div class="form-group">
            <label for="mail" class="col-md-2 control-label">E-mail</label>
            <div class="col-md-10">
                <input name="mail" type="email" class="form-control" placeholder="e-mail" value ="" required />
            </div>
        </div>    
        <div class="form-group">
            <label for="pass1" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input name="pass1" type="password" class="form-control" placeholder="password" value ="" required />
            </div>
        </div>
        <div class="form-group">
            <label for="pass2" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input name="pass2" type="password" class="form-control" placeholder="password" value =""required />
            </div>
        </div>
    </div> 

    <div class="container pourcent">
        <button type="submit" class="btn btn-default  pull-right">Create account</button>
    </div>  
<%@include file="includes/footer2.jsp" %>