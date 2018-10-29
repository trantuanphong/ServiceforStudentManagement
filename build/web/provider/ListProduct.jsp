<%-- 
    Document   : New Product
    Created on : Jul 12, 2018, 2:42:36 PM
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
        <jsp:useBean id="listProduct" class="bean.ListProductBean" scope="page"/>
        <jsp:setProperty name="listProduct" property="providerName" value="${sessionScope.account.accountName}"/>
        <jsp:setProperty name="listProduct" property="serviceID" value="${param.serviceID}"/>

        <jsp:include page="/header.jsp"/>
        
        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>
            <c:if test="${not empty listProduct.service}">
                <div class="col-sm-10">
                <!--Filter-->
                <div class="row">

                    <div class="navbar col-sm-5 navbar-right text-center" style="padding-top: 7px;margin-right: 5px; background-color: #337ab7; color: white">
                        <form class="form-inline" id="filterForm">
                            <div class="form-group">
                                <label for="service">Service: </label>
                                <select class="form-control" id="service" name="serviceID" onchange="document.forms['filterForm'].submit()">
                                    <c:forEach var="ser" items="${listProduct.service}">
                                        <option value="${ser.serviceID}" ${ser.serviceID == param.serviceID ? "selected" : "" }>
                                            ${ser.serviceName}</option>
                                        </c:forEach>
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
                        <div class="panel-heading">
                            <h3 h3 style="text-align: center">PRODUCT LIST</h3>
                        </div>
                        <div class="panel-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Quantity</th>
                                        <th>Unit</th>
                                        <th>Price</th> 
                                        <th>Description</th> 
                                        <th style="text-align: center">Action</th>
                                    </tr>
                                </thead>
                                <tbody id="myTable">
                                    <c:forEach var="pro" items="${listProduct.product}">
                                        <tr>
                                            <td>${pro.productName}</td>
                                            <td>${pro.quantity}</td>
                                            <td>${pro.unit}</td>
                                            <td>${pro.price}</td>
                                            <td>${pro.description}</td>
                                            <td style="text-align: center">
                                                <c:url var="up" value="/provider/ListProduct.jsp">
                                                    <c:param name="action" value="update"/>
                                                    <c:param name="productID" value="${pro.productID}"/>
                                                    <c:param name="productName" value="${pro.productName}"/>
                                                    <c:param name="quantity" value="${pro.quantity}"/>
                                                    <c:param name="price" value="${pro.price}"/>
                                                    <c:param name="description" value="${pro.description}"/>
                                                </c:url>
                                                <a href="${up}" class="btn btn-default">Update</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
            <c:if test="${empty listProduct.service}">
                <h3>Not have any service!</h3>
            </c:if>

        </div>

        <c:if test="${param.action == 'update'
                      or param.error == 'updateError'}">
              <script type="text/javascript">
                  $(window).on('load', function () {
                      $('#updateModal').modal('show');
                  });
              </script>

              <!-- update Modal -->
              <div id="updateModal" class="modal fade in" role="dialog">
                  <div class="modal-dialog">
                      <!-- Modal content-->
                      <div class="modal-content">
                          <div class="modal-body">
                              <form action="/ServiceforStudentManagement/ProviderController" method="POST" data-toggle="validator">
                                  <div class="form-group">
                                      <label for="username">Product Name</label>
                                      <input type="text" class="form-control" id="username" value="${param.productName}" name="productName" required>
                                  </div>
                                  <div class="form-group">
                                      <label for="quantity">Quantity: </label>
                                      <input type="number" class="form-control" id="quantity" min="0" data-error="Must be positive number" value="${param.quantity}" name="quantity" required>
                                      <div class="help-block with-errors"></div> 
                                  </div>
                                  <div class="form-group">
                                      <label for="price">Price: </label>
                                      <input type="number" class="form-control" id="price" min="0" data-error="Must be positive number" value="${param.price}" name="price" required>
                                      <div class="help-block with-errors"></div> 
                                  </div>
                                  <div class="form-group">
                                      <label for="description">Description: </label>
                                      <input type="text" class="form-control" name="description" value="${param.description}" id="description" required>
                                  </div>
                                  <div style="text-align:center">
                                      <button type="submit" class="btn btn-success">Update</button>
                                      <button type="button" class="btn btn-danger" data-dismiss="modal">Cancel</button>
                                  </div>
                                  <input type="hidden" name="productID" value="${param.productID}"/>
                                  <input type="hidden" name="link" value="${pageContext.request.requestURL}"/>
                                  <input type="hidden" name="action" value="updateProduct"/>
                                  <c:if test="${param.error == 'updateError'}">
                                      <h5 style="color: red">UPDATE FALSE!</h5> 
                                  </c:if>
                              </form>
                          </div>
                      </div>
                  </div>
              </div>
        </c:if>

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
