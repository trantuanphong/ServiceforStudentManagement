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
    </head>
    <body>
        <jsp:useBean id="feedbackBean" class="bean.FeedbackBean" scope="page"/>
        <jsp:setProperty name="feedbackBean" property="account" value="${sessionScope.account}"/>
        <jsp:setProperty name="feedbackBean" property="selectType" param="selectType"/>
        
        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-10">
                <!--Filter-->
                <div class="row">
                    <div class="navbar col-sm-5 navbar-right text-center" style="padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white">
                        <form class="form-inline" id="filterForm">
                            <label for="selectType">Filter: </label>
                            <div class="form-group">
                                <select class="form-control" id="selectType" name="selectType" onchange="document.forms['filterForm'].submit()">
                                    <option value="All" ${param.selectType == 'All' ? "selected" : ""}>All</option>
                                    <option value="Seen" ${param.selectType == 'Seen' ? "selected" : ""}>Seen</option>
                                    <option value="Not Seen" ${param.selectType == 'Not Seen' ? "selected" : ""}>Not Seen</option>
                                </select>
                            </div>
                            <div class="input-group" class="text-center">
                                <input type="text" class="form-control" id="filterName" placeholder="Enter account here">
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
                        <div class="panel-heading">
                            <h3 style="text-align: center">FEEDBACK LIST</h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Sender</th>
                                        <th>Title</th>
                                        <th>Detail</th>
                                        <th>Date</th>                 
                                        <th>Status</th>                 
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="myTable">
                                    <c:forEach var="feedback" items="${feedbackBean.feedbackList}">
                                        <tr>
                                            <td>${feedback.senderName}</td>
                                            <td>${feedback.title}</td>
                                            <td>${feedback.detail}</td>
                                            <td>${feedback.date}</td>
                                            <td>${feedback.status}</td>
                                            <td style="text-align: center">
                                                <c:if test="${feedback.status != 'Seen'}">
                                                    <a href="/ServiceforStudentManagement/FeedbackController?action=seenFeedback&feedbackID=${feedback.id}" class="btn btn-default">Seen</a>
                                                </c:if>
                                                <a href="/ServiceforStudentManagement/FeedbackController?action=deleteFeedback&feedbackID=${feedback.id}" class="btn btn-default">Delete</a>
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
