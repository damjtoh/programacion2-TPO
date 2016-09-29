package tda;

public interface TDACola {

	public void inicializar();
	
	/** inicializada */
	public void acolar(String valor, String prioridad);
	
	/** inicializada y no vac�a */
	public void desacolar();
	
	/** inicializada y no vac�a */
	public String primero();
	
	/** inicializada */
	public boolean colaVacia();
	
}
