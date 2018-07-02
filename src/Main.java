import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		AnalisadorLexico main = new AnalisadorLexico();
		Scanner ler = new Scanner(System.in);	
		String s;

		System.out.printf("Digite o caminho do arquivo que deseja realizar a analise lexica:\n");
		s = ler.next();
		
		main.executar(s);
		
		

	}

}
