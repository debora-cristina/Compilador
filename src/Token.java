
public class Token {

	private String lexema;
	private String simbolo;
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public String getSimbolo() {
		return simbolo;
	}
	public Token(String lexema, String simbolo) {
		super();
		this.lexema = lexema;
		this.simbolo = simbolo;
	}
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
}
