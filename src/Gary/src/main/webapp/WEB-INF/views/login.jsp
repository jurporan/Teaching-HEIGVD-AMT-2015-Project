<%-- 
    Document   : login2
    Created on : 08-Oct-2015, 22:43:45
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>

        <form method="POST" action="login"> 
            <input type="hidden" name="action" value="login" />

            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-mail</label>
                <div class="col-sm-10">
                    <input id="email" type="email" class="form-control" placeholder="e-mail" value ="<c:out value="${param.email}"/>" name="email" required/>
                </div>
            </div>

            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input id="password" type="password" class="form-control" placeholder="password" name="password" required/>
                </div>
            </div>
            
                <div>
                    <button type="submit" value="login" class="btn btn-default  pull-right">Login</button>
                    <p class="errors">${result}</p>
                </div>
         
            </form>
</div>

<%@include file="includes/footer.jsp" %>
