<%-- 
    Document   : Payment
    Created on : Jul 10, 2018, 2:09:14 PM
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
            .customtable{
                border-collapse: separate;
                border-spacing: 10px;
            }
            a {
                color: orange;
            }
        </style>
    </head>
    <body >
        <jsp:useBean id="tranBean" class="bean.TransactionBean" scope="page"/>
        <jsp:setProperty name="tranBean" property="accountName" value="${sessionScope.account.accountName}"/>

        <jsp:include page="/header.jsp"/>

        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-7">
                <!--payment-->
                <div class="row">
                    <div class="panel panel-primary">
                        <!--heading-->
                        <div class="panel-heading">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="navbar-brand">TRANSACTION</li>
                                <li role="presentation" class="active">
                                    <a href="#topUp" role="tab" aria-controls="topUp" data-toggle="tab">Top-Up</a>
                                </li>
                                <li role="presentation">
                                    <a href="#trade" role="tab" aria-controls="trade" data-toggle="tab">Payment</a>
                                </li>
                            </ul>
                        </div>

                        <!--body-->
                        <div class="panel-body">
                            <div class="tab-content">
                                <!--top-up-->
                                <div role="tabpanel" class="tab-pane active" id="topUp">
                                    <form action="/ServiceforStudentManagement/AccountController" method="POST" data-toggle="validator"> 
                                        <div class="form-group">
                                            <label for="amount">Amount:</label>
                                            <input type="number" class="form-control" id="amount" placeholder="Enter amount" name="amount" min="0" data-error="Must be positive number" required>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="pwdTop">Password:</label>
                                            <input type="password" class="form-control" id="pwdTop" placeholder="Enter password" name="pwd" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="rePwdTop">Re-password:</label>
                                            <input type="password" class="form-control" id="rePwdTop" data-match="#pwdTop" data-match-error="Doesn't match" placeholder="Re-enter password" name="rePwd" required>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div style="text-align: center">
                                            <button type="submit" class="btn-success">Top-up</button>
                                            <button type="reset" class="btn-warning">Clear</button>
                                        </div>
                                        <input type="hidden" name="link" value="${pageContext.request.requestURL}"/>
                                        <input type="hidden" name="action" value="topUp"/>
                                    </form>
                                </div>
                                <!--trade-->
                                <div role="tabpanel" class="tab-pane" id="trade">
                                    <form action="/ServiceforStudentManagement/AccountController" method="POST" data-toggle="validator">
                                        <div class="form-group">
                                            <label for="receiver">Receiver:</label>
                                            <input type="text" class="form-control" id="receiver" placeholder="Enter receiver name" name="receiver" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="amount">Amount:</label>
                                            <input type="number" class="form-control" id="amount" placeholder="Enter amount" min="0" data-error="Must be positive number" name="amount" required>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="content">Content:</label>
                                            <input type="text" class="form-control" id="content" placeholder="Enter content" name="content" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="pwd">Password:</label>
                                            <input type="password" class="form-control" id="pwdTrade" placeholder="Enter password" name="pwd" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="rePwd">Re-password:</label>
                                            <input type="password" class="form-control" id="rePwd" data-match="#pwdTrade" data-match-error="Doesn't match" placeholder="Re-enter password" name="rePwd" required>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div style="text-align: center">
                                            <button type="submit" class="btn-success">Send</button>
                                            <button type="reset" class="btn-warning">Clear</button>
                                        </div>
                                        <input type="hidden" name="link" value="${pageContext.request.requestURL}"/>
                                        <input type="hidden" name="action" value="payment"/>
                                    </form>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>

                <!--history-->
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
                                <tbody>        
                                    <c:forEach var="pro" items="${tranBean.history}">
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

            <c:if test="${param.error == 'transError'}">
                <script type="text/javascript">
                    $(window).on('load', function () {
                        $('#noticeTransModal').modal('show');
                    });
                </script>
                <div class="modal fade" id="noticeTransModal" role="dialog">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Notice</h4>
                            </div>
                            <div class="modal-body">
                                <p>Transaction fail!</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
                
            <jsp:include page="/informationBar.jsp"/>
        </div>
    </body>
</html>
