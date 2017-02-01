package com.tsvikdima.cloudnote.password;
import java.security.MessageDigest;

public class SHA {
	
	public static void main(String[] args) throws Exception {
		System.out.println(""+passTwo("1234","1234"));
		System.out.println(""+pass("1234"));

	}

	
	//encryptionPassword
	static String pass(String password) throws Exception {


		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}

		return sb.toString();
	}
	
	
	static boolean passTwo(String password, String password_two) throws Exception {
		boolean b = false;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		
		
		MessageDigest md1 = MessageDigest.getInstance("SHA-256");
		md1.update(password_two.getBytes());

		byte byteData2[] = md1.digest();
		
		StringBuffer sb1 = new StringBuffer();
		for (int i = 0; i < byteData2.length; i++) {
			sb1.append(Integer.toString((byteData2[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		System.out.println(""+sb.toString());
		System.out.println(""+sb1.toString());

		if (sb.toString().contains(sb1.toString())) {
			b = true;
		} else {
			return b;

		}
		return b;

	}
}