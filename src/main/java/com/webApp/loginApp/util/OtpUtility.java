package com.webApp.loginApp.util;

public class OtpUtility {

	public static Integer genOtp() {
		Integer max = 9999, min = 1000;		  
		Integer randomNumber = (int)(Math.random()*(max-min+1)+min);
		System.out.println("Random value of type int between " + min + " to " + max + " : " + randomNumber);
		return randomNumber;
	}
}
