<%@ page import="com.payment"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.6.0.js"></script> 
<script src="Components/Payment.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-5"> 
			<h1>ElectroGrid Payment Management</h1>
				<form id="formPayment" name="formPayment" method="post" action="Payment.jsp">  
					Customer Name:  
 	 				<input id="customerName" name="customerName" type="text"  class="form-control form-control-sm">
					<br>Account Number:   
  					<input id="accountNum" name="accountNum" type="text" class="form-control form-control-sm">   
  					<br>Payment Date:   
  					<input id="Paydate" name="Paydate" type="text"  class="form-control form-control-sm">
  					<br>Payment Email:   
  					<input id="Pemail" name="Pemail" type="text"  class="form-control form-control-sm">
  					<br>Total Amount:   
  					<input id="totAmount" name="totAmount" type="text"  class="form-control form-control-sm">
  					<br>
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidPaymentnIDSave" name="hidPaymentIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divPaymentGrid">
					<%
					    
					    payment paymentObj = new payment(); 
						out.print(paymentObj.readPayment());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>