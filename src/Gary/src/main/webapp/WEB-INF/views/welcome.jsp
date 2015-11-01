<%-- 
    Document   : welcome
    Created on : 08-Oct-2015, 22:37:36
    Author     : mel
--%>

<%@include file="includes/header.jsp" %>
			
<h1 class="mainTitle"><img src="resources/img/title.png" /></h1>
    
    <div class="stats">
        <p>${totalOfAccounts} accounts have been created in total.</p>
        <p>${totalOfApps} applications have been created in total.</p>
        <p>${numberOfUsersCreatedByApplicationsDuringThe30LastDays} users created by apps during the 30 last days.</p>
    </div>
    
    <div class="inscrutions">
        Please <a href="login" id="loginLink">login</a> or <a href="registration" id="registrationLink">create an account</a> to continue.
    </div>

<%@include file="includes/footer.jsp" %>