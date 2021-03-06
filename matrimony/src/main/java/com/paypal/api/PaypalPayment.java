/**
 * 
 */
package com.paypal.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.paypal.svcs.types.ap.PayRequest;
import com.paypal.svcs.types.ap.PayResponse;
import com.paypal.svcs.types.ap.PaymentDetailsRequest;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.ap.Receiver;
import com.paypal.svcs.types.ap.ReceiverList;
import com.paypal.svcs.types.common.RequestEnvelope;

/**
 * @author SON
 *
 */
public class PaypalPayment {

	public static PayResponse pay(double money, String returnUrl, String cancelUrl, String currencyCode){
		RequestEnvelope requestEnvelope = new RequestEnvelope("en_US");
		List<Receiver> receivers = new ArrayList<Receiver>();

		Receiver receiver = new Receiver();
		receiver.setAmount(money);
		receiver.setEmail(CredentialsConfiguration.TREASURE_EMAIL);
		receivers.add(receiver);

		ReceiverList receiverList = new ReceiverList(receivers);

		PayRequest payRequest = new PayRequest();
		payRequest.setRequestEnvelope(requestEnvelope);
		payRequest.setReceiverList(receiverList);
		payRequest.setCurrencyCode(currencyCode);
		payRequest.setActionType("PAY");
		payRequest.setCancelUrl(cancelUrl);
		payRequest.setReturnUrl(returnUrl);

		PayResponse payResponse=null;

		try {
			payResponse = PaypalAPI.getService().pay(payRequest);
			String value = payResponse.getResponseEnvelope().getAck().getValue();
			if (PaypalAPI.FAILURE.equalsIgnoreCase(value))
				System.out.println("PayResponse: " + payResponse.getError().get(0).getMessage());
		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payResponse;
	}

	public static PaymentDetailsResponse checkPaymentByTransactionId(String transactionId){
		RequestEnvelope requestEnvelope=new RequestEnvelope("vi_VN");
		PaymentDetailsRequest paymentDetailsRequest=new PaymentDetailsRequest(requestEnvelope);
		paymentDetailsRequest.setTransactionId(transactionId);
		
		PaymentDetailsResponse paymentDetailsResponse=null;
		try {
			paymentDetailsResponse = PaypalAPI.getService().paymentDetails(paymentDetailsRequest);
			String value=paymentDetailsResponse.getResponseEnvelope().getAck().getValue();
			System.out.println("PaymentDetailsResponse: " +value);
			if(PaypalAPI.FAILURE.equalsIgnoreCase(value))
				System.out.println("PaymentDetailsResponse: "+paymentDetailsResponse.getError().get(0).getMessage());
		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paymentDetailsResponse;
	}
	public static PaymentDetailsResponse checkPaymentByPayKey(String payKey){
		RequestEnvelope requestEnvelope=new RequestEnvelope("vi_VN");
		PaymentDetailsRequest paymentDetailsRequest=new PaymentDetailsRequest(requestEnvelope);
		paymentDetailsRequest.setPayKey(payKey);
		
		PaymentDetailsResponse paymentDetailsResponse=null;
		try {
			paymentDetailsResponse = PaypalAPI.getService().paymentDetails(paymentDetailsRequest);
			String value=paymentDetailsResponse.getResponseEnvelope().getAck().getValue();
			System.out.println("PaymentDetailsResponse: " +value);
			if(PaypalAPI.FAILURE.equalsIgnoreCase(value))
				System.out.println("PaymentDetailsResponse: "+paymentDetailsResponse.getError().get(0).getMessage());
		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paymentDetailsResponse;
	}
}
