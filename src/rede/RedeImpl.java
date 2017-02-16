package rede;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.rede.*;
import ui.Ui;

public class RedeImpl implements Rede, Recebedor, AguardarServidor, ThreadEnvioException {
	private Network network;
	private List<String> ltexto;
	private String ipServidor;
	private Thread thread;
	private ThreadEnvio threadEnvio;

	public RedeImpl() {
		this.ipServidor = "";
		this.network = new NetworkImpl();
		this.ltexto = new ArrayList<String>();
		this.threadEnvio = new ThreadEnvio(network, RedeImpl.this);
		this.receberTexto();
		this.aguardarServidor();
	}

	public void aguardarServidor() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					network.aguardarServidor(1078, RedeImpl.this);
				} catch (IOException e) {
					System.out.println("Aguardar Servidor: " + e.getMessage());
					setIpServidor("");
				}
			}
		}).start();
	}

	@Override
	public void enviarTexto(String texto) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				if (!texto.equalsIgnoreCase("cache")){
					getLtexto().add(texto);
				}else
					Ui.textoLivre(getLtexto().toString());
			}
		});
		t.start();
		t.interrupt();
	}

	@Override
	public void receberTexto() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					network.serverSocketReceive(1077, RedeImpl.this);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					Ui.erroEnderecoUso();
				}
			}
		}).start();
	}

	@Override
	public void receiveMessage(String texto, String ip) {
		String textoSemCorrigir = texto.substring(0, texto.indexOf("-"));

		if (this.getLtexto().contains(textoSemCorrigir))
			Ui.removendoCache(textoSemCorrigir);
		this.getLtexto().remove(textoSemCorrigir);

		Ui.textoRecebido(texto, ip);
	}

	@Override
	public void receberOk(String ip) {
		this.ipServidor = ip;
		if (!getLtexto().isEmpty()) {
			threadEnvio.setIp(ip);
			for (String texto : getLtexto()) {
				threadEnvio.setTexto(texto);
				thread = new Thread(threadEnvio);
				thread.start();
				thread.interrupt();
			}
		}
	}

	public List<String> getLtexto() {
		return ltexto;
	}

	public void setLtexto(List<String> ltexto) {
		this.ltexto = ltexto;
	}

	public String getIpServidor() {
		return ipServidor;
	}

	public void setIpServidor(String ipServidor) {
		this.ipServidor = ipServidor;
	}

	@Override
	public void envioException(Exception e) {
		if (thread.isAlive()) {
			thread.stop();
		}
	}
}
