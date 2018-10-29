<%-- 
    Document   : NewProvider
    Created on : Jul 12, 2018, 1:14:36 PM
    Author     : Phong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Service Manager</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-7">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 style="text-align: center">CREATE NEW ACCOUNT</h3>
                    </div>
                    <div class="panel-body">
                        <form id="filter">
                            <div class="form-group">
                                <label for="type">Type:</label>
                                <select class="form-control" id="type" name="type" onchange="document.forms['filter'].submit()">
                                    <option value="provider" ${param.type == 'provider' ? "selected" : ""}>Provider</option>
                                    <option value="student" ${param.type == 'student' ? "selected" : ""}>Student</option>
                                    <option value="admin" ${param.type == 'admin' ? "selected" : ""}>Administrator</option>
                                </select>
                            </div>
                        </form>
                        <form action="/ServiceforStudentManagement/AdminController" method="POST" data-toggle="validator">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input value="${sessionScope.un}" type="text" class="form-control" id="username" placeholder="Enter username" name="username">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.usernameerror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="fullname">Full Name</label>
                                <input value="${sessionScope.fn}" type="text" class="form-control" id="fullname" placeholder="Enter full name" name="fullname">
                            </div>

                            <div class="form-group">
                                <label for="pwd">Password:</label>
                                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="pwd">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.passworderror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="repwd">Re-password:</label>
                                <input type="password" class="form-control" id="repwd" placeholder="Re-enter password" name="repwd">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.repassworderror}
                                </p>
                            </div>
                            <c:if test="${param.type == 'student'}">
                                <div class="form-group">
                                    <label for="roleNumber">Role Number:</label>
                                    <input value="${sessionScope.rn}" type="text" class="form-control" id="roleNumber" placeholder="Enter role number" name="roleNumber">
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input value="${sessionScope.em}" type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.emailerror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone:</label>
                                <input value="${sessionScope.ph}" type="number" data-minlength="9" data-maxlength="10" class="form-control" id="phone" placeholder="Enter phone" name="phone">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.phoneerror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="address">Address:</label>
                                <input value="${sessionScope.add}"  type="text" class="form-control" id="address" placeholder="Enter your address" name="address">
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.addresserror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="date">Date of birth:</label>
                                <input value="${sessionScope.da}" type="date" class="form-control" id="date" name="date">
                            </div>
                            <div style="text-align:center">
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="submit" class="btn btn-warning" value="clear" name="action" >Clear</button>
                            </div>
                            <input type="hidden" name="link" value="${pageContext.request.requestURL}"/>
                            <input type="hidden" name="action" value="newAccount"/>
                            <input type="hidden" name="type" value="${param.type}"/>
                        </form>
                    </div>
                </div>
            </div>

            <jsp:include page="/informationBar.jsp"/>            
        </div>
    </body>
</html>
