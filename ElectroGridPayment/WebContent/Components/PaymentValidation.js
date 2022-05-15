$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validatePaymentForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "PaymentAPI",   
			type : type,  
			data : $("#formPayment").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onPaymentSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onPaymentSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divPaymentGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidPaymentIDSave").val("");  
	$("#formPayment")[0].reset(); 
} 

 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());     
	$("#description").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#adminName").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#nDate").val($(this).closest("tr").find('td:eq(2)').text());
	$("#zone").val($(this).closest("tr").find('td:eq(3)').text());   
	 
}); 




//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "PaymentAPI",   
		type : "DELETE",   
		data : "PaymentID=" + $(this).data("PaymentID"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onPaymentDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onPaymentDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divPaymentGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validatePaymentForm() 
{  
	// customerName-----------------------
	if ($("#customerName").val().trim() == "")  
	{   
		return "Insert Customer name";  
	} 

	
	// AccountNum------------------------------
	if ($("#AccountNum").val().trim() == "")  
	{   
		return "Insert Account Number";  
	}
	
	//payDate-------------------------------
	if ($("#payDate").val().trim() == "")  
	{   
		return "Insert Pay date";  
	}
	
	//  Pemail---------------------------  
	if ($("#Pemail").val().trim() == "")  
	{   
		return "Insert Payment email";  
	}
		
	// totAmount---------------------------  
	if ($("#totAmount").val().trim() == "")  
	{   
		return "Insert Total amount";  
	}
			
		
	return true; 
}