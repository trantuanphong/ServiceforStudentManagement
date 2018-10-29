<%-- 
    Document   : Feedback
    Created on : Jul 11, 2018, 8:15:16 AM
    Author     : Tu Khac Hieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href='https://fonts.googleapis.com/css?family=Open Sans' rel='stylesheet' type='text/css'>
        <link href="https://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
        <link rel="stylesheet" href="../src/css/feedbackAd.css">
        <title>FeedBack</title>
    </head>
    <body>
        <jsp:useBean id="service" class="bean.FeedbackBean" scope="request"/>
        <jsp:setProperty name="service" property="account" value="${sessionScope.account}"/>
        <div id="content">
            <h1>Feedback</h1>

            <form method="post" autocomplete="on">
                <div class="form-group">
                    <label for="username" class="icon-user"> Receiver 
                        <span class="required">*</span>
                    </label>
                    <select class="form-control" id="type" name="type" onchange="document.forms[0].submit()">
                        <option value="admin" ${param.type == 'admin' ? "selected" : ""}>Administrator</option>
                        <c:if test="${not empty service.serviceList}">
                            <option value="provider" ${param.type == 'provider' ? "selected" : ""}>Provider</option>
                        </c:if>  
                    </select>

                </div>
            </form>

            <form action="/ServiceforStudentManagement/FeedbackController">                  
                <c:if test="${param.type == 'provider'}">
                    <div class="form-group">
                        <label for="serviceName" class="icon-user"> Service Name 
                            <span class="required">*</span>
                        </label>

                        <select class="form-control" id="service" name="receiver">
                            <c:forEach var="ser" items="${service.serviceList}">
                                <option value="${ser.providerName}" ${param.serviceName == ser.serviceName ? "selected" : ""}>${ser.serviceName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>   
                <p>
                    <label for="subject" class="icon-bullhorn"> Subject</label>
                    <input type="text" name="subject" id="subject" placeholder="What would you like to talk about?" />
                </p>
                <p>
                    <label for="message" class="icon-comment"> Message
                        <span class="required">*</span>
                    </label>
                    <textarea name="detail" placeholder="Your message here and I'll answer as soon as possible " required="required"></textarea>
                </p>
                <p class="icon-comment">Fields with
                    <span class="required"> * </span>are required</p>
                <p>
                    <button type="submit" class="btn btn-info" value=" Send this mail ! "  /> Send this mail </button>
                    <a href="/ServiceforStudentManagement/Home.jsp">
                        <button type="button" class="btn btn-default">Cancel</button>
                    </a>
                    <input type="hidden" name="sender" value="${sessionScope.account.accountName}"/>
                    <input type="hidden" name="action" value="newFeedback"/>
            </form>
        </div>
    </body>
</html>
