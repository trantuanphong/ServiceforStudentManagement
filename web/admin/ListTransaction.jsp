<%-- 
    Document   : AllAccount
    Created on : Jul 11, 2018, 9:36:57 AM
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
        <style>
            th, td {
                text-align: center;
            }
        </style>
    </head>
    <body>
        <jsp:useBean id="tranBean" class="bean.TransactionBean" scope="page"/>
        <jsp:setProperty name="tranBean" param="type" property="selectType"/> 
        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-10">
                <!--Filter-->
                <div class="row">
                    <div class="navbar col-sm-6 navbar-right text-center" style="padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white">
                        <form class="form-inline" id="filterForm">
                            <div class="form-group">
                                <label for="type">Filter: </label>
                                <select class="form-control" id="type" name="type" onchange="document.forms['filterForm'].submit()"> 
                                    <option value="All" ${param.type == 'All' ? "selected" : ""}>All</option>
                                    <option value="Topup" ${param.type == 'Topup' ? "selected" : ""}>Top-Up</option>
                                    <option value="Payment" ${param.type == 'Payment' ? "selected" : ""}>Payment</option>
                                </select>
                            </div>
                            <div class="input-group" class="text-center">
                                <input type="text" class="form-control" id="filterName" placeholder="Enter name">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit">
                                        <i class="glyphicon glyphicon-search"></i>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!--Table data-->
                <div class="row">
                    <div class="panel panel-primary">
                        <div class="panel-heading text-center">
                            <h3>HISTORY</h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Account Name</th>
                                        <th>Purpose</th>
                                        <th>Value</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>
                                <tbody id="myTable">        
                                <c:forEach var="pro" items="${tranBean.allHistory}">
                                    <tr>
                                        <td>${pro.accountName}</td>
                                        <td>${pro.purpose}</td>
                                        <td>${pro.value}</td>
                                        <td>${pro.date}</td>                                       
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
