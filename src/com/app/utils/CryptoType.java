package com.app.utils;

public enum CryptoType {
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");
	
	private String string;
	
	CryptoType(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}
