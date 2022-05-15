<%@ page import="model.Billing" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1><b><center>Billing Form </center></b></h1>
<form id="formItem" name="formItem">
  Account Number : 
 <input id="AccNo" name="AccNo" type="text" 
 class="form-control form-control-sm">
 <br> Bill Unit : 
 <input id="BillUnit" name="BillUnit" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Billing billingeObj = new Billing(); 
	 out.print(billingeObj.readbilling()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
