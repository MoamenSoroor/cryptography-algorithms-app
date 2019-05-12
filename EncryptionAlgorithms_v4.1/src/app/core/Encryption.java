package app.core;

public interface Encryption {
	
	public String encrypt(String key, String plainText);

	public String decrypt(String key, String cipherText);
	

}
