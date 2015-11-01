<%-- 
    Document   : editaccount
    Created on : 31-Oct-2015, 16:18:41
    Author     : mel
--%>

<%@include file="includes/header.jsp" %>

    <form method="POST" action="editaccount">
        <input type="hidden" name="action" value="editaccount" class="form-horizontal"/>

        <div class="form-group">
            <label for="firstname" class="col-sm-2 control-label">First name</label>
            <div class="col-sm-10">
                <input id="firstname" name="firstname" type="text" class="form-control" placeholder="first name" value ="<c:out value="${param.firstname}"/>" required />
                <span class="errors">${errors['names']}</span>
            </div>
        </div>

        <div class="form-group">
            <label for="lastname" class="col-sm-2 control-label">Last name</label>
            <div class="col-sm-10">
                <input id="lastname" name="lastname" type="text" class="form-control" placeholder="last name" value ="<c:out value="${param.lastname}"/>" required />
                <span class="errors">${errors['names']}</span>
            </div>
        </div>     

        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">Password</label>
            <div class="col-sm-10">
                <input id="password" name="password" type="password" class="form-control" placeholder="password" value ="password" maxlength="20" required />
            </div>
        </div>

        <div class="form-group">
            <label for="passwordConfirmation" class="col-sm-2 control-label">Password confirmation</label>
            <div class="col-sm-10">
                <input id="passwordConfirmation" name="passwordConfirmation" type="password" class="form-control" placeholder=""  maxlength="20" required />
                <span class="errors">${errors['password']}</span>
            </div>
        </div>

        <button type="submit" value="editaccount" class="btn btn-default  pull-right">Save modifications</button>      
        <p class="${empty errors ? 'succes' : 'errors'}">${result}</p>
        
    </form>
        
<%@include file="includes/footer.jsp" %>