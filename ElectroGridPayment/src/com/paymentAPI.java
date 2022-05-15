package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/PaymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	payment PaymentObj = new payment(); 
	
    public paymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = PaymentObj.insertPayment(
				request.getParameter("customerName"),      
				request.getParameter("accountNum"),
				request.getParameter("Paydate"),
				request.getParameter("Pemail")
				request.getParameter("totAmount")
				);
				
				response.getWriter().write(output);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = PaymentObj.updatePayment(
				    paras.get("hidPaymentIDSave").toString(),     
		    		paras.get("customerName").toString(),     
		    		paras.get("accountNum").toString(),
		    		paras.get("Paydate").toString(),
		    		paras.get("totAmount").toString(), null);
		 
		    		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 Map paras = getParasMap(request); 		 
		 String output = PaymentObj.deletePayment(paras.get("PaymentID").toString());  		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}


}

	
	
	