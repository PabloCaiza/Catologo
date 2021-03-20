package com.marcos.paypal;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.google.gson.Gson;
import com.marcos.beans.SessionController;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;



@WebServlet("/PayPalServlet")
public class PayPalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(PayPalServlet.class);
	@Inject 
	SessionController session;

    /**
     * Default constructor. 
     */
    public PayPalServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("generar orden de paypal");
		PayPalCreateOrder payPalCreateOrder = new PayPalCreateOrder();
		//HttpSession session=request.getSession(false);
		
		if(session!=null) {
			
			HttpResponse<Order> order=payPalCreateOrder.crearOrden(session);
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(order));
		
			
			
		}
		
	}

}
