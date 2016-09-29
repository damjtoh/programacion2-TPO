package tda;

public interface IAgenda {

	/**
	 * @
	 * Define el comportamiento de la agenda de los medicos del consultorio.
	 * @author Claudio
	 * 
	 * */
	
	public void inicializar();
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Agrega un nuevo turno para un paciente para un medico determinado en una fecha determinada. El comportamiento es
	 * similar al que presenta un diccionario multiple ante la existencia o no de los valores.
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @param fecha    : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B> 
	 * @param paciente : Cadena de caracteres con el nombre del paciente 
	 * @param turno    : Cadena de caracteres con el horario del turno. El formato es <B>HH:MM</B> 
	 *                   los mismos son a intervalos de 15 minutos y el primero del d�a es 
	 *                   08:00 y el �ltimo turno es 17:45
	 * @return void
	 *                    
	 **/
	public void agregar(String medico, String fecha, String paciente, String turno);
	
	/** <B>inicializada.</B><BR><BR> 
	 * 
	 * Elimina un medico y toda la informacion asociada
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @return nada
	 * 
	 * */
	public void eliminar(String medico);

	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Elimina una fecha determinada a un medico determinado. Si no existe no hace nada.
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @param fecha    : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD<B>
	 * @return void
	 * 
	 * */
	public void eliminarFecha(String medico, String fecha);
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Elimina el o los turnos de un paciente determinado en una fecha determinada a un medico determinado. Si 
	 * el m�dico o la fecha no existe no hace nada
	 * @param medico   : Cadena de caracteres con el nombre del m�dico
	 * @param fecha    : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @param paciente : Cadena de caracteres con el nombre del paciente
	 * @return void
	 * 
	 * */
	public void eliminarTurno(String medico, String fecha, String paciente);
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todos los m�dicos que tienen turnos en el consultorio.
	 * @return TDAConjunto con los nombres de los m�dicos 
	 * 
	 * */
	public TDAConjunto obtenerMedicos();
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todas las fechas que tienen turnos en el consultorio ordenadas de menor a mayor.
	 * @return TDACola con las fechas en las que hay turnos 
	 * 
	 * */
	public TDAColaPrioridad obtenerFechas();
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene todas las fechas que tienen turnos en el consultorio para un 
	 * m�dico determinado, ordenadas de menor a mayor.
	 * @param medico : Cadena de caracteres con el nombre del m�dico
	 * @return TDACola con las fechas en las que hay turnos 
	 * */
	public TDAColaPrioridad obtenerFechasMedico(String medico);
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene todos los turnos que tiene el consultorio para una 
	 * fecha determinada, ordenados de menor a mayor.
	 * @param fecha : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @return Arreglo bidimensional que tiene en cada fila la fecha, el medico, el paciente y el turno,
	 * ordenado por fecha, medico y turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosFecha(String fecha);
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todos los turnos que tienen en el consultorio para un 
	 * m�dico determinado, ordenados de menor a mayor.
	 * @param medico : Cadena de caracteres con el nombre del m�dico
	 * @return Arreglo bidimensional que tiene en cada fila la fecha, el paciente y el turno,
	 * ordenado por fecha y turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosMedico(String medico);
	
	/** <B>inicializada.</B><BR><BR>
	 * 
	 * Obtiene el conjunto de todos los turnos que tienen en el consultorio para un 
	 * m�dico determinado en una fecha determinada, ordenados de menor a mayor.
	 * @param medico : Cadena de caracteres con el nombre del m�dico
	 * @param fecha : Cadena de caracteres con la fecha del turno. El formato es <B>YYYYMMDD</B>
	 * @return Arreglo bidimensional que tiene en cada fila la el paciente y el turno,
	 * ordenado por turno 
	 * 			  
	 * */
	public String[][] obtenerTurnosMedicoEnFecha(String medico, String fecha);

}
