package com.marcos.paypal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.marcos.beans.SessionController;
import com.marcos.dto.CarritoProducto;
import com.paypal.http.HttpRequest;
import com.paypal.http.HttpResponse;
import com.paypal.orders.AddressPortable;
import com.paypal.orders.AmountBreakdown;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Item;
import com.paypal.orders.Money;
import com.paypal.orders.Name;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.Payer;
import com.paypal.orders.PurchaseUnitRequest;
import com.paypal.orders.ShippingDetail;

/*
 * 
 * Clase que se encarga de generar la estructura y la orden de la informacion
 * de  la compra que desea realizar el usuario Paypal
 */
public class PayPalCreateOrder extends PayPalClient {
	private OrderRequest orderRequest;
	double impuestoSumaTotalProductos;
	private static final org.apache.logging.log4j.Logger LOGGER= LogManager.getLogger(PayPalCreateOrder.class);

	/*
	 * Metodo para generar la orden de comrpo de los productos
	 */
	public HttpResponse<Order> crearOrden(SessionController session) throws IOException {
		LOGGER.info("Ingresando a la funcion crear orden");
		OrdersCreateRequest ordersCreateRequest=new OrdersCreateRequest();
		ordersCreateRequest.prefer("return=representation");
		ordersCreateRequest.requestBody(this.generarCuerpoOrder(session));
		HttpResponse<Order> response =client().execute(ordersCreateRequest);
		session.setOrder(response);
		return response;
	}

	private OrderRequest generarCuerpoOrder(SessionController session) {
		// se genera el objeto de solicitud
		this.orderRequest = new OrderRequest();
		// cliente que compra
		Payer payer = new Payer();
		this.orderRequest.checkoutPaymentIntent("CAPTURE");
		ApplicationContext applicationContext = new ApplicationContext().brandName("Vanatci").landingPage("BILLING")
				.shippingPreference("SET_PROVIDED_ADDRESS");
		
		this.orderRequest.applicationContext(applicationContext);
		String nombre = session.getPersona().getNombre();
		String correo = session.getPersona().getCorreo();
		
		
		List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<PurchaseUnitRequest>();
		List<Item> items = new ArrayList<Item>();

		List<CarritoProducto> carritoProductos = session.getPersona().getCarrito().getCarritosProducto();

		double impuestoPorProducto = 10;
		impuestoSumaTotalProductos = 0;

		carritoProductos.forEach(carrito -> {
			Item item = new Item();
			item.name(carrito.getProducto().getNombre());
			item.category("PHYSICAL_GOODS");
			item.description(carrito.getProducto().getDescripcion());

			item.unitAmount(new Money().currencyCode("USD").value(String.valueOf(carrito.getProducto().getPrecio())));
			item.tax(new Money().currencyCode("USD").value(String.valueOf(impuestoPorProducto)));
			item.quantity(String.valueOf(carrito.getCantidad()));
			item.sku("sku1");

			// se configura el detalle de envio
			items.add(item);
			impuestoSumaTotalProductos += impuestoPorProducto * carrito.getCantidad();

		});

		ShippingDetail shippingDetail = new ShippingDetail();

		shippingDetail.name(new Name().fullName(nombre));
		shippingDetail.addressPortable(new AddressPortable().addressLine1("mi calle 1").addressLine2("mi calle 2")
				.adminArea1("Ecuador").adminArea2("Quito").postalCode("593").countryCode("EC"));

		DecimalFormat decimalFormat = new DecimalFormat("#.##");

		double totalConDecimales = Double.valueOf(decimalFormat.format(session.getTotalCompra()));

		double envio = 20;
		double handling = 10;
		double descuentoEnvio = 20;

		double totalCompraConImpuesto = totalConDecimales + impuestoSumaTotalProductos + handling + envio
				- descuentoEnvio;

		PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest();
		purchaseUnitRequest.items(items);
		purchaseUnitRequest.shippingDetail(shippingDetail);
		purchaseUnitRequest.amountWithBreakdown(
				new AmountWithBreakdown().currencyCode("USD").value(String.valueOf(totalCompraConImpuesto))
						.amountBreakdown(new AmountBreakdown()
								.itemTotal(new Money().currencyCode("USD").value(String.valueOf(totalConDecimales)))
								.shipping(new Money().currencyCode("USD").value(String.valueOf(envio)))
								.handling(new Money().currencyCode("USD").value(String.valueOf(handling)))
								.taxTotal(new Money().currencyCode("USD").value(String.valueOf(impuestoSumaTotalProductos)))
						.shippingDiscount(new Money().currencyCode("USD").value(String.valueOf(descuentoEnvio)))));
		
		
		purchaseUnitRequests.add(purchaseUnitRequest);
		this.orderRequest.purchaseUnits(purchaseUnitRequests);
		payer.addressPortable(purchaseUnitRequest.shippingDetail().addressPortable());
		this.orderRequest.payer(payer);
		Gson gson=new Gson();
		LOGGER.info(gson.toJson(purchaseUnitRequests));
		
		return this.orderRequest;
		

	}

}
