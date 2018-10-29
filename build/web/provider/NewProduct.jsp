<%-- 
    Document   : NewProduct
    Created on : Jul 16, 2018, 5:55:42 PM
    Author     : Phong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <jsp:useBean id="listService" class="bean.RegisterProduct" scope="page"/>
        <jsp:setProperty name="listService" property="providerName" value="${sessionScope.account.accountName}"/>


        <div class="container-fluid row">

            <jsp:include page="/functionBar.jsp"/>

            <div class="col-sm-7">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 style="text-align: center">CREATE NEW PRODUCT</h3>
                    </div>
                    <div class="panel-body">
                        <form action="/ServiceforStudentManagement/ProviderController" method="" data-toggle="validator">
                            <div class="form-group">
                                <label for="serviceName">Service Name: </label>
                                <select name="drServiceName" class="btn btn-default">
                                    <c:forEach var="a" items="${listService.service}">
                                        <option value="${a.serviceID}">${a.serviceName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="productName">Product Name: </label>
                                <input value="${sessionScope.proname}" type="text" class="form-control" id="productName" placeholder="Enter product name" name="txtProductName" required>
                            </div>
                            <p style="color:red;font-style: italic;">
                                ${sessionScope.productnameerror}
                            </p>
                            <div class="form-group">
                                <label for="price">Price: </label>
                                <input value="${sessionScope.proprice}" type="number" class="form-control" min="0" data-error="Must be positive number" id="price" placeholder="Enter price" name="txtPrice" required>
                                <div class="help-block with-errors"></div>
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.priceerror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="quantity">Quantity: </label>
                                <input value="${sessionScope.proquantity}" type="number" min="0" data-error="Must be positive number" class="form-control" id="price" placeholder="Enter quantity" name="txtQuantity"required>
                                <div class="help-block with-errors"></div>
                                <p style="color:red;font-style: italic;">
                                    ${sessionScope.quantityerror}
                                </p>
                            </div>
                            <div class="form-group">
                                <label for="unit">Unit: </label>
                                <label class="radio-inline"><input type="radio" name="txtUnit" value="piece" required>Piece</label>
                                <label class="radio-inline"><input type="radio" name="txtUnit" value="day" required>Day</label>
                            </div>
                            <p style="color:red;font-style: italic;">
                                ${sessionScope.uniterror}
                            </p>
                            <div class="form-group">
                                <label for="description">Description: </label>
                                <textarea class="form-control" rows="5" id="description" name="txtDescription" required></textarea>
                            </div>
                            <p style="color:red;font-style: italic;">
                                ${sessionScope.deserror}
                            </p>
                            <div style="text-align:center">
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="submit" value="clear" name="action" class="btn btn-warning">Clear</button>
                            </div>
                            <input type="hidden" name="action" value="newProduct"/>
                        </form>
                    </div>
                </div>
            </div>

            <jsp:include page="/informationBar.jsp"/>            
        </div>
    </body>
</html>
