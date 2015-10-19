<%-- 
    Document   : login2
    Created on : 08-Oct-2015, 22:43:45
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>

    <form class="form-vertical" role="form " />
    
    <input type="hidden" name="action" value="login" />
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">E-mail</label>
        <div class="col-sm-10">
            <input type="email" class="form-control" placeholder="e-mail" name="email" />
        </div>
    </div>
    
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Password</label>
        <div class="input-group col-sm-10">
            <input type="password" class="form-control" placeholder="password" name="password" />
        </div>
    </div>

    <div class="container pourcent">
        <button type="submit" class="btn btn-default  pull-right" name="action" >Login</button>
    </div>
    
</div>2

<%@include file="includes/footer.jsp" %>
