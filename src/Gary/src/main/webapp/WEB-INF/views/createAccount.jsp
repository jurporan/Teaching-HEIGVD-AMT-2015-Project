<%-- 
    Document   : createAccount
    Created on : 08-Oct-2015, 22:38:04
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>
    <form class="form-vertical" role="form">
        <div class="form-group">
            <label for="email" class="col-md-2 control-label">E-mail</label>
            <div class="col-md-10">
                <input type="email" class="form-control" placeholder="e-mail" value ="">
            </div>
        </div>    
        <div class="form-group">
            <label for="password" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input type="password" class="form-control" placeholder="password" value ="">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-md-2 control-label">Password</label>
            <div class="col-md-10">
                <input type="password" class="form-control" placeholder="password" value ="">
            </div>
        </div>
    </div> 

    <div class="container pourcent">
        <button type="submit" class="btn btn-default  pull-right">Create account</button>
    </div>  
<%@include file="includes/footer2.jsp" %>