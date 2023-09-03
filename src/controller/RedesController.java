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
		String os = os();
		if (os.contains("Windows")) {
			try {
				String adaptador = "";
				
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
			
		}else {
			try {
				
				Process ipaddr = Runtime.getRuntime().exec("ip addr");//Recebe o fluxo de dados do processo 
				InputStream fluxo = ipaddr.getInputStream(); //Recebe o fluxo de dados codificados em bits
				InputStreamReader leitor = new InputStreamReader(fluxo); //Lê o fluxo de saída de console do processo e converte para String
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();				
				while (linha != null) {
					String[] adaptador = {};
					if (linha.contains("mtu")) {
						adaptador = linha.split(": ");
					}
					if (linha.contains("inet ")) {
						String[] ipv4 = linha.split(" ");
						System.out.println(adaptador[1]+"\nIPv4: "+ipv4[1]);
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
	
	public void ping(String url) {
		String os = os();
//		String[] pingMedio = new String[4];
		String coman;
		if (os.contains("Windows")){
			coman = "-n";
		} else {
			coman = "-c";
		}
		try{
			StringBuffer bufferS = new StringBuffer();
			bufferS.append("ping -4 -n 10 ");
			bufferS.append(url);
			Process pingIpv4 = Runtime.getRuntime().exec(bufferS.toString()); //Faz a chamada do ping por ipv4 da url recebida como parâmetro 

			InputStream fluxo = pingIpv4.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader bufferR = new BufferedReader(leitor);
			String linha = bufferR.readLine();
			
			if (os.contains("Windows")) {
				while (linha != null){
					if (linha.contains("Média")) {
						String[] pingMedio = linha.split("= ");
						System.out.println("Tempo médio do ping: "+pingMedio[2]);
					}
					linha = bufferR.readLine();//Lê a próxima linha
				}
				
			} else {
				while (linha != null){
					if (linha.contains("rtt")) {
						String[] pingMedio = linha.split("/");
						System.out.println("Tempo médio do ping: "+pingMedio[4]);
					}
				}
				
				linha = bufferR.readLine(); //Lê a próxima linha
			}
			bufferR.close();
			leitor.close();
			fluxo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
