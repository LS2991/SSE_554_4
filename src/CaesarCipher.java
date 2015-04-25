
public class CaesarCipher {
	
	private static final int ASCII_MAX = 126;
	private static final int ASCII_MIN = 33;
	private static int shift;
	
	
	public CaesarCipher(int shift) {
		this.shift = shift;
	}
	//Limited to Ascii only
	public String encrypt(String input) {
		String encrypted = "";
		for(int x = 0; x < input.length(); x++) {
			char c = input.charAt(x);
			if(c + shift > ASCII_MAX){
				int delta = ((c + shift) - (ASCII_MAX)) - 1;
				c = (char) (ASCII_MIN + delta);
			}
			else {
				c += shift;
			}
			
			encrypted += c;
		}
		
		return encrypted;
	}
	
	public String decrypt(String encrypted) {
		String decrypted = "";
		for(int x = 0; x < encrypted.length(); x++) {
			char c = encrypted.charAt(x);
			if(c - shift < ASCII_MIN) {
				int delta = (ASCII_MIN - (c - shift)) - 1;
				c = (char) (ASCII_MAX - delta);
			}
			else {
				c -= shift;
			}
			
			decrypted += c;
		}
		
		return decrypted;
	}
}
