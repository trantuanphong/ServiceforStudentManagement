<%-- 
    Document   : ViewBill
    Created on : Jul 18, 2018, 5:52:16 PM
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
        <jsp:useBean id="billList" class="bean.BillBean" scope="page"/>
        <jsp:setProperty name="billList" property="account" value="${sessionScope.account}"/>

        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-10">

                <!--Filter-->
                <div class="row">
                    <div class="navbar col-sm-6 navbar-right text-center" style="padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white">
                        <form class="form-inline">
                            <div class="form-group">
                                <label for="type">Filter: </label>
                                <select class="form-control" id="type" name="type" onchange="document.forms[4].submit()">
                                    <option value="all" ${param.type == 'all' ? "selected" : ""}>All</option>
                                    <option value="request" ${param.type == 'request' ? "selected" : ""}>Request</option>
                                    <option value="reject" ${param.type == 'reject' ? "selected" : ""}>Reject</option>
                                    <option value="finished" ${param.type == 'finished' ? "selected" : ""}>Finished</option>
                                </select>
                            </div>
                            <div class="input-group" class="text-center">
                                <input type="text" class="form-control" id="filterName" placeholder="Enter name" id="filterName" name="filterName">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!--Table-->
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 style="text-align: center">BILL LIST</h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Service Name</th>
                                        <th>Provider Name</th> 
                                            <c:if test="${sessionScope.account.type == 2}">
                                            <th>User Name</th> 
                                            </c:if>
                                        <th>Price</th> 
                                        <th>Detail</th> 
                                        <th>Status</th> 
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="myTable"> 
                                    <c:forEach var="bill" items="${billList.bill}">
                                        <tr>
                                            <jsp:setProperty name="billList" property="serviceID" value="${bill.serviceID}"/>
                                            <td>${billList.serviceName} </td>
                                            <td>${bill.providerName}</td>
                                            <c:if test="${sessionScope.account.type == 2}">
                                                <td>${bill.userName}</td>
                                            </c:if>
                                            <td>${bill.price}</td>
                                            <td>${bill.detail}</td>
                                            <td>${bill.status}</td>
                                            <td style="text-align: center">
                                                <c:if test="${sessionScope.account.type == 1}">
                                                    <a href="/ServiceforStudentManagement/BillController?action=payBill" class="btn btn-default">
                                                        Payment
                                                    </a>
                                                    <a href="/ServiceforStudentManagement/BillController?action=changeStatusBill&status=reject" class="btn btn-default">
                                                        Reject
                                                    </a>
                                                </c:if>
                                                <c:if test="${sessionScope.account.type == 2}">
                                                    <a href="/ServiceforStudentManagement/BillController?action=changeStatusBill&status=cancel" class="btn btn-default">
                                                        Cancel
                                                    </a>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                
        <script>
            $(document).ready(function () {
                $("#filterName").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </body>
</html>
