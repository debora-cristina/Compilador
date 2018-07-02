import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LerTexto {

	public String lerArquivo() throws IOException {

		// abertura do arquivo
		BufferedReader myBuffer = new BufferedReader(
				new InputStreamReader(new FileInputStream("arquivo.txt"), "UTF-8"));
		// loop que lê e imprime todas as linhas do arquivo
		String gerarString = new String();
		String linha = myBuffer.readLine();
		while (linha != null) {
			gerarString = gerarString + linha + "\n";
			linha = myBuffer.readLine();
		}
		myBuffer.close();

	return gerarString;
	}

}
