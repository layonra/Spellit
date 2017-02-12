package rede;
import java.util.List;
import java.util.ArrayList;

public class InicializarListenerThreadException {
	
	private List<ThreadEnvioException> listener = new ArrayList<ThreadEnvioException>();
	
	public void adicionarListener(ThreadEnvioException addTo) {
		this.listener.add(addTo);
	}
	
	public void catchException(Exception e, ThreadEnvioException addTo) {
		this.adicionarListener(addTo);
		for(ThreadEnvioException envioException : listener) {
			envioException.envioException(e);
		}
	}	

}
