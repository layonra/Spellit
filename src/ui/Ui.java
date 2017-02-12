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
		System.out.println("\nTexto Enviado para " + ip + ": " + texto);
	}
	
	public static void statusServidor(String status) {
		System.out.println("Status do Servidor: " + status);
	}
	
	public static void erroEnderecoUso() {
		System.out.println("Ops!\nJá existe um cliente aberto");
	}
	
	public String escreverAlgo(){
		Scanner scan = new Scanner(System.in);
		System.out.print("Escreva algo: ");
		String texto = scan.next();
		return texto;
	}
	
	public static void texto(String texto) {
		System.out.println("" + texto);
	}
}