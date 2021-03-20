package com.marcos.paypal;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class PayPalClient {

	/**
	 * Set up the PayPal Java SDK environment with PayPal access credentials. This
	 * sample uses SandboxEnvironment. In production, use LiveEnvironment.
	 */
	private PayPalEnvironment environment = new PayPalEnvironment.Sandbox(
			"AbldJgJ5lNsV1zXUNtzDhvbytP4tQj00f0DHhsOyOEj7w58sHY9SIOPKtsfqFUiiyeKN8HWarUjDHLWV",
			"EFkxSufN62hnfIs8tC_VPL0ZYkwPaxdkQPXd7bZV8_G7FXcTYUkZqdZN1ObqpsiaVhHg8E5iMzw_haIH");

	/**
	 * PayPal HTTP client instance with environment that has access credentials
	 * context. Use to invoke PayPal APIs.
	 */
	PayPalHttpClient client = new PayPalHttpClient(environment);

	/**
	 * Method to get client object
	 *
	 * @return PayPalHttpClient client
	 */
	public PayPalHttpClient client() {
		return this.client;
	}
}