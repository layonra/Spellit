package rede;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rede.*;
import ui.Ui;

public class RedeImpl implements Rede, Recebedor {
	private Network network;
	private List<String> ltexto = new ArrayList<String>();
	private String ip;

	public RedeImpl() {
		this.ip = "";
		this.network = new NetworkImpl();
		this.receberTexto();
	}

	public String aguardarServidor() {
		String ip = "";
		try {
			ip = network.aguardarServidor(1078);
		} catch (IOException e) {
			System.out.println("" + e.getMessage());
		}
		return ip;
	}

	@Override
	public void enviarTexto(String texto) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					if (ip.equals(""))
						ip = aguardarServidor();
					
					for (String texto : ltexto) {
						Ui.textoEnviado(texto, ip);
						network.sendIPPacket(texto, ip, 2078);
					}
					ltexto.clear();
				} catch (IOException ioe) {
					ip = "";
				}
			}
		});
		thread.start();
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
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void receiveMessage(String texto, String ip) {
		Ui.textoRecebido(texto, ip);
	}

}
