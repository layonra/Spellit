package ui;

import ctrl.Controller;

public class MainCliente {
	
	public static void main(String args[]) {
		Controller controller = Controller.getInstance();
		Ui ui = new Ui();
		
		while(true) {
			String texto = ui.escreverAlgo();
			controller.enviarTexto(texto);
		}
	}
}
