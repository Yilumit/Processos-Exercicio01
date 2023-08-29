package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	public void ip() {
		String adaptador = "";
		String os = os();
		if (os.contains("Windows")) {
			try {
				Process ipconfig = Runtime.getRuntime().exec("ipconfig");//Recebe o fluxo de dados do processo 
				InputStream fluxo = ipconfig.getInputStream(); //Recebe o fluxo de dados codificados em bits
				InputStreamReader leitor = new InputStreamReader(fluxo); //Lê o fluxo de saída de console do processo e converte para String
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();				
				while (linha != null) {
					
					if (linha.contains("Adaptador")) {
						adaptador = linha;
					}
					if (linha.contains("IPv4")) {
						String[] ipv4 = linha.split(": ");
						System.out.println(adaptador+"\nIPv4: "+ipv4[1]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
