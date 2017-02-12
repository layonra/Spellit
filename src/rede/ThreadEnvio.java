package rede;

import java.io.IOException;
import br.com.rede.Network;
import ui.Ui;

public class ThreadEnvio implements Runnable {
	private Network network;
	private String texto;
	private String ip;
	private ThreadEnvioException addTo;
	private InicializarListenerThreadException listener;
	
	public ThreadEnvio(Network network, ThreadEnvioException addTo) {
		this.network = network;
		this.addTo = addTo;
		this.listener = new InicializarListenerThreadException();
	}
	
	@Override
	public void run() {
		try {
			Ui.textoEnviado(this.texto, this.ip);
			this.network.sendIPPacket(this.texto, this.ip, 2078);			
		} catch (IOException ioe) {
			Ui.statusServidor(Ui.OFFLINE);	
			this.listener.catchException(ioe, this.addTo);
		}
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
