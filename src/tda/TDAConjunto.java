package tda;

public interface TDAConjunto {

	public void inicializar();
	
	/** inicializada */
	public void agregar(String valor);
	
	/** inicializada */
	public void sacar(String valor);
	
	/** inicializada y no vacia */
	public String elegir();
	
	/** inicializada */
	public boolean pertenece(String valor);
	
	/** inicializada */
	public boolean conjuntoVacio();
}
