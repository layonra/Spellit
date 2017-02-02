package ui;

import java.util.Scanner;

/**
 *
 * @author layon
 */
public final class Ui {
	
	public static final String ONLINE = "Online";
	public static final String OFFLINE = "Offline";
	public static final String AGUARDANDO = "Aguardando";
	
	public static void textoRecebido(String texto, String ip) {
		System.out.println("Texto Corrigido por "  + ip + ": " + texto);
	}
	
	public static void textoEnviado(String texto, String ip) {
		System.out.println("Texto Enviado para " + ip + ": " + texto);
	}
	
	public static void statusServidor(String status) {
		System.out.println("Status do Servidor: " + status);
	}
	
	public String escreverAlgo(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Escreva algo: ");
		String texto = scan.next();
		return texto;
	}
}