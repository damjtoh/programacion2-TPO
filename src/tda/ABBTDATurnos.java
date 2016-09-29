package tda;

public interface ABBTDATurnos {

	public void inicializar();
	
	/**
	 * <B>inicializada.</B><BR><BR>
	 * Agrega un paciente a un turno determinado.
	 * @param paciente : cadena de caracteres con el nombre del paciente
	 * @param turno : cadena de caracteres con la hora del turno 
	 * @return void 
	 * 
	 * */
	public void agregar(String paciente, String turno);

	/**
	 * <B>inicializada.</B><BR><BR>
	 * Elimina un paciente a un turno determinado.
	 * @param turno : cadena de caracteres con el turno del paciente
	 * @return void 
	 * */
	public void eliminar(String turno);
	
	/**
	 * <B>inicializada y no vacia.</B><BR><BR>
	 * @return : el nombre del paciente de la raiz dada.
	 * */
	public String paciente();
	
	/**
	 * <B>inicializada y no vacia.</B><BR><BR>
	 * @return : el turno del paciente de la raiz dada.
	 * 
	 * */
	public String turno();
	
	/**
	 * <B>inicializada y no vacia.</B><BR><BR>
	 * @return : el subarbol izquierdo de la raiz dada
	 * 
	 * */
	public ABBTDATurnos hijoIzquierdo();
	
	/**
	 * <B>inicializada y no vacia.</B><BR><BR>
	 * @return : el subarbol derecho de la raiz dada
	 * 
	 * */
	public ABBTDATurnos hijoDerecho();
	
	/**
	 * <B>inicializada.</B><BR><BR>
	 * @return : true si el arbol esta vac�o o false si no lo esta 
	 *  
	 * */
	public boolean arbolVacio();
}
