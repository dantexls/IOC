/*
 * FileName:com.iss.service.MD5.java
 *
 * V1.0 Oct 18, 2012 11:35:46 AM created by YanJun.Xu
 *
 * CopyRight (c) 2012 iSoftStone (Group) Co., Ltd. 
 * All Rights Reserved.
 * Confidential and for internal user only.
 */
package com.iss.cityioc.scs.common.util;

import java.security.MessageDigest;

/**
 * 
 * TODO 描述类的功能
 *
 * @author YanJun Xu
 * @email yjxuf@isoftstone.com
 * @date Oct 18, 2012 9:35:24 PM
 */
public class MD5 {
	private String inStr;
	private MessageDigest md5;

	/**
	 * Constructs the MD5 object and sets the string whose MD5 is to be
	 * computed.
	 * 
	 * @param inStr the <code>String</code> whose MD5 is to be computed
	 */
	public MD5(String inStr) {
		this.inStr = inStr;
		try {
			this.md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	/**
	 * Computes the MD5 fingerprint of a string.
	 * 
	 * @return the MD5 digest of the input <code>String</code>
	 */
	public String compute() {
		// convert input String to a char[]
		// convert that char[] to byte[]
		// get the md5 digest as byte[]
		// bit-wise AND that byte[] with 0xff
		// prepend "0" to the output StringBuffer to make sure that we don't end
		// up with
		// something like "e21ff" instead of "e201ff"

		char[] charArray = this.inStr.toCharArray();

		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = this.md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	public static void main(String[] args) {
		MD5 md5 = new MD5("abc");
		String postString = md5.compute().toUpperCase();
		System.out.println(postString);
		if (postString.equals("900150983CD24FB0D6963F7D28E17F72")) {
			System.out.println("true");
		} else
			System.out.println("false");
	}
}