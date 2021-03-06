/**
 * 
 */
package com.matrimony.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javassist.expr.NewArray;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.INPUT_STREAM;

import com.matrimony.util.IOUtils;
import com.matrimony.util.MailUtil;
import com.paypal.api.PaypalPayment;
import com.paypal.svcs.types.ap.PaymentDetailsResponse;
import com.paypal.svcs.types.ap.PaymentInfo;
import com.paypal.svcs.types.common.PhoneNumberType;

/**
 * @author SON
 *
 */
public class Printer {
	/**
	 * 
	 */
	private String billDocument;

	public Printer() {
		InputStream is = getClass().getResourceAsStream("/resoucres/billTemplate.txt");
		try {
			billDocument = IOUtils.toString(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String printBillPos(PaymentDetailsResponse pdr, Timestamp time) {
		PaymentInfo pi = pdr.getPaymentInfoList().getPaymentInfo().get(0);
		String billText = billDocument;
		billText = billText.replaceAll("totalMoney", String.valueOf(pi.getReceiver().getAmount()));
		billText = billText.replace("status", pi.getSenderTransactionStatus());
		billText = billText.replace("transactionId", pi.getSenderTransactionId());
		billText = billText.replace("payEmail", pdr.getSender().getEmail());
		PhoneNumberType numberType = pdr.getSender().getPhone();
		System.out.println(numberType);
		if (numberType != null) {
			billText = billText.replace("payPhone", numberType.getCountryCode() + " " + numberType.getPhoneNumber());
		} else {
			billText = billText.replace("payPhone", "");
		}
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat df3 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		billText = billText.replace("payDate", df.format(time));
		billText = billText.replace("payTime", df2.format(time));
		if (pi.getReceiver().getAmount() == 499.99)
			billText = billText.replace("payMonth", String.valueOf(12));
		else
			billText = billText.replace("payMonth", String.valueOf(1));
		billText = billText.replace("currentTime", df3.format(new Timestamp(System.currentTimeMillis())));
		return billText;
	}

	public static void main(String[] args) {
		
	}
}
