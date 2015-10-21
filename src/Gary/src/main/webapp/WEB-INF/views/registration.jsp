<%-- 
    Document   : createAccount
    Created on : 08-Oct-2015, 22:38:04
    Author     : mel
--%>

<%@include file="includes/header2.jsp" %>
    <div class="container pourcent">
        <form method="POST" action="registration">
            <input type="hidden" name="action" value="registration"/>
            
            <div class="form-group">
                <label for="firstname" class="col-md-2 control-label">First name</label>
                <div class="col-md-10">
                    <input name="firstname" type="text" class="form-control" placeholder="first name" value ="<c:out value="${param.firstname}"/>" required />
                    <span class="errors">${errors['names']}</span>
                </div>
            </div>

            <div class="form-group">
                <label for="lastname" class="col-md-2 control-label">Last name</label>
                <div class="col-md-10">
                    <input name="lastname" type="text" class="form-control" placeholder="last name" value ="<c:out value="${param.lastname}"/>" required />
                    <span class="errors">${errors['names']}</span>
                </div>
            </div>     
            
            <div class="form-group">
                <label for="email" class="col-md-2 control-label">E-mail</label>
                <div class="col-md-10">
                    <input name="email" type="email" class="form-control" placeholder="e-mail" value ="<c:out value="${param.email}"/>" maxlength="60" required />
                    <span class="errors">${errors['email']}</span>
                </div>
            </div>    
            
            <div class="form-group">
                <label for="password" class="col-md-2 control-label">Password</label>
                <div class="col-md-10">
                    <input name="password" type="password" class="form-control" placeholder="password" value ="password" maxlength="20" required />
                    <span class="errors">${errors['password']}</span>
                </div>
            </div>
            
            <div class="form-group">
                <label for="passwordConfirmation" class="col-md-2 control-label">Password confirmation</label>
                <div class="col-md-10">
                    <input name="passwordConfirmation" type="password" class="form-control" placeholder=""  maxlength="20" required />
                    <span class="errors">${errors['password']}</span>
                </div>
            </div>

        <button type="submit" value="registration" class="btn btn-default  pull-right">Create account</button>
        
        <p class="${empty errors ? 'succes' : 'errors'}">${result}</p>
        
    </div>  
<%@include file="includes/footer.jsp" %>