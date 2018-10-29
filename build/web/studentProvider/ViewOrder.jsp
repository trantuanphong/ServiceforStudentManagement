<%-- 
    Document   : ViewOrder
    Created on : Jul 16, 2018, 3:31:43 PM
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
        <jsp:useBean id="viewOrder" class="bean.OrderBean" scope="page"/>
        <jsp:setProperty name="viewOrder" property="account" value="${sessionScope.account}"/>
        <jsp:setProperty name="viewOrder" property="selectType" param="type"/>

        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-10">

                <!--Filter-->
                <div class="row">
                    <div class="navbar col-sm-6 navbar-right text-center" style="padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white">
                        <form class="form-inline" id="filter">
                            <div class="form-group">
                                <label for="type">Filter: </label>
                                <select class="form-control" id="type" name="type" onchange="document.forms['filter'].submit()">
                                    <option value="all" ${param.type == 'all' ? "selected" : ""}>All</option>
                                    <option value="Request" ${param.type == 'Request' ? "selected" : ""}>Request</option>
                                    <option value="Shipping" ${param.type == 'Shipping' ? "selected" : ""}>Shipping</option>
                                    <option value="In-Use" ${param.type == 'In-Use' ? "selected" : ""}>In-Use</option>
                                    <option value="Finished" ${param.type == 'Finished' ? "selected" : ""}>Finished</option>
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
                            <h3 style="text-align: center">ORDER LIST</h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Service Name</th>
                                            <c:if test="${sessionScope.account.type == 2}">
                                            <th>User</th>
                                            </c:if>
                                        <th>Destination</th> 
                                        <th>Price</th> 
                                        <th>Quantity</th> 
                                        <th>Start</th> 
                                        <th>End</th> 
                                        <th>Status</th> 
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="myTable"> 
                                    <c:forEach var="ord" items="${viewOrder.order}">
                                        <tr>
                                            <jsp:setProperty name="viewOrder" property="serviceID" value="${ord.serviceID}"/>
                                            <jsp:setProperty name="viewOrder" property="productID" value="${ord.productID}"/>
                                            <c:if test="${not empty viewOrder.serviceName}">
                                                <td>${viewOrder.productName} </td>
                                                <td>${viewOrder.serviceName.get(0).getServiceName()}</td>
                                                <c:if test="${sessionScope.account.type == 2}">
                                                    <td>${ord.userName}</td>
                                                </c:if>
                                                <td>${ord.destination}</td>
                                                <td>${ord.price}</td>
                                                <td>${ord.quantity}</td>
                                                <td>${ord.startDate}</td>
                                                <td>${ord.endDate}</td>
                                                <td>${ord.status}</td>
                                                <td style="text-align: center">
                                                    <c:if test="${sessionScope.account.type == 1}">
                                                        <a href="/ServiceforStudentManagement/studentProvider/Feedback.jsp" class="btn btn-default">
                                                            Feedback
                                                        </a>
                                                        <a href="/ServiceforStudentManagement/user/ServiceDetail.jsp?serviceID=${ord.serviceID}" class="btn btn-default">
                                                            View
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${sessionScope.account.type == 2 and ord.status == 'Request'}">
                                                        <a href="/ServiceforStudentManagement/OrderController?action=changeStatusOrder&status=accept&orderID=${ord.orderID}" class="btn btn-default">
                                                            Accept
                                                        </a>
                                                        <a href="/ServiceforStudentManagement/OrderController?action=changeStatusOrder&status=reject&orderID=${ord.orderID}" class="btn btn-default">
                                                            Reject
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${sessionScope.account.type == 2 and ord.status == 'Shipping'}">
                                                        <a href="/ServiceforStudentManagement/OrderController?action=changeStatusOrder&status=finish&orderID=${ord.orderID}" class="btn btn-default">
                                                            Finish
                                                        </a>
                                                    </c:if>
                                                </td>
                                            </c:if>
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
