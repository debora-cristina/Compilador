import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorLexico {

	ArrayList<Token> arrayToken = new ArrayList<>();
	private List<String> ltTokens = new LinkedList<>();
	private Stack pilha = new Stack();
	Lexemas lex = new Lexemas();

	public BufferedReader lerArquivo(String nomeArquivo) throws UnsupportedEncodingException, FileNotFoundException {
		FileReader arq = new FileReader(nomeArquivo);
		BufferedReader myBuffer = new BufferedReader(arq);
		return myBuffer;
	}

	public void salvarTokens(String path) throws IOException {
		FileWriter arq = new FileWriter(path);

		try (PrintWriter printArq = new PrintWriter(new OutputStreamWriter (new FileOutputStream (path), "UTF-8"))) {
			for (Token a : arrayToken) {
				if (a.getSimbolo().matches("[0-9]+")) {
					printArq.println(a.getSimbolo() + " - numerico inteiro");
				} else if (a.getSimbolo().matches("[0-9]+[.][0-9]+")) {
					printArq.println(a.getSimbolo() + " - numerico real");
				} else if (a.getSimbolo().matches("['\"].+['\"]")) {
					printArq.println(a.getSimbolo() + " - sequencia char entre aspas");
				} else if (a.getLexema() == null) {
					if (a.getSimbolo().matches("[^\\s]*[^\\x00-\\x7F]+[^\\s]*")) {
						printArq.println(a.getSimbolo() + " - identificador invalido");
					} else {
						printArq.println(a.getSimbolo() + " - identificador");
						a.setLexema("identificador");
					}
				} else {

					printArq.print(a.getSimbolo() + " - " + a.getLexema() + " ");
					printArq.println("");
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Caught IndexOutOfBoundsException: " + e.getMessage());

		} finally {
			if (arq != null) {
				System.out.println("txt construido com sucesso");
				arq.close();
			} else {
				System.out.println("PrintWriter not open");
			}
		}
	}

	public void printTokens() {
		String auxiliar = "";
		for (String a : ltTokens) {
			if (a.equals("end_of_line") && auxiliar.isEmpty()) {
				auxiliar = a;
				arrayToken.add(new Token(lex.getLexema(a), a));
			} else if (a.equals(auxiliar)) {
				auxiliar = "";
			} else {
				auxiliar = "";
				arrayToken.add(new Token(lex.getLexema(a), a));
			}
		}

	}
	
	public void substituirStrings(BufferedReader in, Pattern regex, boolean primeira) throws IOException {
		File file = new File("arquivo1.txt");
		String linha, texto = "";
		boolean primeiraLinha = true;
		int cont =0;
		while ((linha = in.readLine()) != null) {
			texto += linha + "\n";
			cont++;
            
		}

		StringBuffer sb = new StringBuffer();
		Matcher m = regex.matcher(texto);

		while (m.find()) {
			String rep = m.group();
			m.appendReplacement(sb, " " + rep + " ");
		}
		m.appendTail(sb);
		texto = sb.toString();

		if (primeira) {
			texto = replaceString(texto);
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(texto);
		bw.close();
		in.close();
	}

	public void substituir(BufferedReader in) throws IOException {
		Pattern regex = Pattern.compile(
				"(\".+\") |(\\++)|(\\--)|(>=)|(!=)|(<=)|(\\|=)|(\\*=)|(\\/=)|(-=)|(%=)|(\\/\\/=)|(%=)|(\\^=)|(~=)|(>>=)|(<<=)|(\\*\\*=)|(@=)|(<>)|(\\+)"
						+ "|(-)|(//)|(/)|(^)|(%)|(==)|(\\<<)|(\\>>)|(=)|(\\*\\*)|(\\<<)|(\\>>)|(\\<)|(\\>)");

		substituirStrings(in, regex, true);

		BufferedReader resultado = lerArquivo("arquivo1.txt");

		Pattern pParentheses = Pattern.compile("(\\(|\\)|\\[|\\]|\\{|\\})");
		substituirStrings(resultado, pParentheses, false);
	}

	public void analisar(String nomeArquivo) throws IOException, FileNotFoundException {
		// Token token = new Token("token", "simbolo");

		BufferedReader myBuffer = lerArquivo(nomeArquivo);
		String linha;
		String auxiliar = "";
		String fim = "n";
		boolean isTrue = true;

		substituir(myBuffer);

		BufferedReader resultado = lerArquivo("arquivo1.txt");;
		Integer cont = 0;
		int qtdLinhas = 0;
		
		while ((linha = resultado.readLine()) != null) {
			cont = 0;
			qtdLinhas++;

			for (char a : linha.toCharArray()) {

				String caractere = Character.toString(a);
				if (caractere.equals("\t")) {
					cont++;
				} else if (!caractere.equals("\t")) {
					empilhar(cont);
				}
				if (Character.isWhitespace(a) && isTrue) {
					if (!auxiliar.isEmpty()) {
						ltTokens.add(auxiliar);
						auxiliar = "";
					}
				} else {
					if ((caractere.equals("\"") || caractere.equals("\'"))) {
						if (fim.equals("n")) {
							fim = "m";
							isTrue = !isTrue;
						} else if (fim.equals("m")) {
							ltTokens.add(caractere);
							isTrue = true;
							fim = "n";
						}
						auxiliar += caractere;
					} else if (!isTrue) {

						auxiliar += caractere;
					} else {

						auxiliar += caractere;
					}
				}
			}


		}

		while (!pilha.empty()) {
			pilha.pop();
			ltTokens.add("end");
		}

		myBuffer.close();

	}

	public void empilhar(Integer cont) {
		if (pilha.empty()) {
			pilha.push(cont);
			ltTokens.add("indent");
		}
		if (cont > (Integer) (pilha.peek())) {
			pilha.push(cont);
			ltTokens.add("indent");
		} else {
			while (cont < (Integer) (pilha.peek())) {
				pilha.pop();
				ltTokens.add("dedent");
			}
		}
	}

	public String replaceString(String texto) {
		texto = texto.replaceAll("\uFFFD", "\"");
		texto = texto.replaceAll("[#](.)*", "");
		texto = texto.replaceAll("(?m)^[ \t]*\r?\n", "");
		texto = texto.replaceAll("(\'\'\'[\\s\\S]*?\'\'\')|(\"\"\"[\\s\\S]*?\"\"\")", " ");
		texto = texto.replaceAll("(\\r|\\n)", " end_of_line \n");
		texto = texto.replace("﻿", "");
		texto = texto.replace("&", " & ");
		texto = texto.replace("~", " ~ ");
		texto = texto.replace(";", " ; ");
		texto = texto.replace(".", " . ");
		texto = texto.replace(",", " , ");
		texto = texto.replace(":", " : ");
		texto = texto.replace("@", " @ ");
		texto = texto.replace("$", " $ ");
		texto = texto.replace("?", " ? ");

		return texto;
	}

	public void executar(String nomeArquivo) throws IOException {
		analisar(nomeArquivo);
		printTokens();
		salvarTokens("saida");
	}

}
