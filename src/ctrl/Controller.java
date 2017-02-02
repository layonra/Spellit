package ctrl;

import rede.ReceberTextoInterface;
import rede.Rede;
import rede.RedeImpl;
import ui.Ui;

public class Controller implements ReceberTextoInterface{
	private static Controller controller;
	private Rede rede;

	public Controller() {
		this.rede = new RedeImpl();
	}



	public static Controller getInstance() {
		if (controller == null)
			controller = new Controller();
		return controller;
	}

	public void enviarTexto(String texto) {
		this.rede.enviarTexto(texto);
	}
	
	@Override
	public void receberTexto(String texto, String ip) {
		Ui.textoRecebido(texto,ip);
	}

}
