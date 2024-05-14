package jmybatis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class test123 {
	public static void main(String[] args) {
		String a = "b7acb073d3e5bb5ad2e01b59cfceeaee04e1ea0e";

		for (int i = 10000000; i <= 99999999; i++) {
			String pw = Integer.toString(i) + "salt_for_you";
			for (int j = 0; j < 500; j++) {
				pw = sha1(pw);
			}
			System.out.println("process" + i);
			if (pw.equals(a)) {
				System.out.println("Find Flag : " + i + "salt_for_you");
				break;
			}
		}
	}

	public static String sha1(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] result = md.digest(input.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : result) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
