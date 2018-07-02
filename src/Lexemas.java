import java.util.HashMap;
import java.util.LinkedHashMap;

public class Lexemas {

	private static HashMap<String, String> lexemas;

	public static HashMap<String, String> getLexemas() {
		return lexemas;
	}

	public static void setLexemas(String lexema, String simbolo) {
		lexemas.put(lexema, simbolo);
	}

	public String getLexema(String lexema) {
		return lexemas.get(lexema);
	}

	public Lexemas() {

		lexemas = new LinkedHashMap<String, String>();

		lexemas.put("and", "Palavra reservada");
		lexemas.put("del", "Palavra reservada");
		lexemas.put("for", "Palavra reservada");
		lexemas.put("is", "Palavra reservada");
		lexemas.put("raise", "Palavra reservada");
		lexemas.put("assert", "Palavra reservada");
		lexemas.put("elif", "Palavra reservada");
		lexemas.put("from", "Palavra reservada");
		lexemas.put("lambda", "Palavra reservada");
		lexemas.put("return", "Palavra reservada");
		lexemas.put("break", "Palavra reservada");
		lexemas.put("else", "Palavra reservada");
		lexemas.put("global", "Palavra reservada");
		lexemas.put("not", "Palavra reservada");
		lexemas.put("try", "Palavra reservada");
		lexemas.put("class", "Palavra reservada");
		lexemas.put("except", "Palavra reservada");
		lexemas.put("if", "Palavra reservada");
		lexemas.put("or", "Palavra reservada");
		lexemas.put("while", "Palavra reservada");
		lexemas.put("continue", "Palavra reservada");
		lexemas.put("exec", "Palavra reservada");
		lexemas.put("import", "Palavra reservada");
		lexemas.put("pass", "Palavra reservada");
		lexemas.put("yield", "Palavra reservada");
		lexemas.put("def", "Palavra reservada");
		lexemas.put("finally", "Palavra reservada");
		lexemas.put("in", "Palavra reservada");
		lexemas.put("print", "Palavra reservada");
		lexemas.put("nonlocal", "Palavra reservada");
		lexemas.put("with", "Palavra reservada");
		lexemas.put("as", "Palavra reservada");
		lexemas.put("not", "Palavra reservada");
		lexemas.put("True", "Palavra reservada");
		lexemas.put("False", "Palavra reservada");
		lexemas.put("None", "Palavra reservada");


		lexemas.put("+", "operador");
		lexemas.put("++", "operador");
		lexemas.put("--", "operador");
		lexemas.put("-", "operador");
		lexemas.put("*", "operador");
		lexemas.put("/", "operador");
		lexemas.put("%", "operador");
		lexemas.put("**", "operador");
		lexemas.put("//", "operador");
		lexemas.put("<<", "operador");
		lexemas.put(">>", "operador");
		lexemas.put("&", "operador");
		lexemas.put("|", "operador");
		lexemas.put("^", "operador");
		lexemas.put("~", "operador");
		lexemas.put("<", "operador");
		lexemas.put("<=", "operador");
		lexemas.put(">", "operador");
		lexemas.put(">=", "operador");
		lexemas.put("<>", "operador");
		lexemas.put("!=", "operador");
		lexemas.put("==", "operador");
		lexemas.put("=", "operador");

		// delimitadorimitadores

		// delimitadorimitadores da linguagem python

		lexemas.put("(", "delimitador");
		lexemas.put(")", "delimitador");
		lexemas.put("{", "delimitador");
		lexemas.put("}", "delimitador");
		lexemas.put("[", "delimitador");
		lexemas.put("]", "delimitador");
		lexemas.put(",", "delimitador");
		lexemas.put(":", "delimitador");
		lexemas.put(".", "delimitador");
		lexemas.put("=", "delimitador");
		lexemas.put(";", "delimitador");
		lexemas.put("+=", "delimitador");
		lexemas.put("-=", "delimitador");
		lexemas.put("*=", "delimitador");
		lexemas.put("/=", "delimitador");
		lexemas.put("//=", "delimitador");
		lexemas.put("%=", "delimitador");
		lexemas.put("|=", "delimitador");
		lexemas.put("^=", "delimitador");
		lexemas.put(">>=", "delimitador");
		lexemas.put("<<=", "delimitador");
		lexemas.put("**=", "delimitador");
		lexemas.put("'","delimitador");
		lexemas.put("\"","delimitador");
		lexemas.put("#","delimitador");
		lexemas.put("/","delimitador");
		lexemas.put("indent", "");
		lexemas.put("dedent", "");
		lexemas.put("end_of_line", "");
		lexemas.put("begin", "");
		lexemas.put("end", "");
		lexemas.put("@", "Erro!Caractere invalido.");
		lexemas.put("$", "Erro!Caractere invalido.");
		lexemas.put("?", "Erro!Caractere invalido.");


	}

}
