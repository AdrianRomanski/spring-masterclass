<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <title>Shop</title>
    <%@ include file="resources.jsp" %>
</head>

<body>
<%@ include file="menu.jsp" %>
<div class="container">
    <h1>Add user</h1>
    <sf:form modelAttribute="user" method="post">
        <div class="form-group">
            <label for="firstName">First name</label>
            <sf:input path="firstName" class ="form-control"></sf:input>
            <sf:errors path="firstName"/>
            <label for="firstName">Last name</label>
            <sf:input path="lastName" class ="form-control"></sf:input>
            <sf:errors path="lastName"/>
            <label for="firstName">Email</label>
            <sf:input path="email" class ="form-control"></sf:input>
            <sf:errors path="email"/>
        </div>
        <button type="submit" class="btn btn-primary">Save</button>
    </sf:form>
</div>

</body>
</html>