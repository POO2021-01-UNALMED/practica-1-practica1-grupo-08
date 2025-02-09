package gestorAplicacion.Personal;

import java.io.Serializable;
/* Autores: Ver�nica Seguro Varela
 * Componentes: Constructores, m�todo asignarsalario()
 * Finalidad: Crear empleados con diferentes cargos que permitan un correcto
 * funcionamiento del hotel. */
public class OficiosVarios extends Empleado {
 
// El siguiente atributo es necesario para la serizalizaci�n de las instancias de esta clase.
	private static final long serialVersionUID = 1L;

	//CONSTRUCTORES 
/* Se usar�n los constructores de su clase padre Empleado, el tipo de constructor a usar
	 * depender� si la empleado tiene horas extras por pagar o no. 
	 * 
	 * El cargo que se debe ingresar debe ser espec�ficamente uno de los siguientes:
	 * chef, recepcionista, vigilante o bartender. 
	 * */
	public OficiosVarios(String nombre, long id, String cargo, HorasExtras horasextras, int cantidadHoras) {
		super(nombre,id,cargo,horasextras,cantidadHoras);
	}

	public OficiosVarios(String nombre, long id, String cargo) {
		super(nombre,id,cargo);
		
	}

	// M�TODO
	
	/* Este m�todo buscar� que tipo de cargo tiene el empleado ya sea chef, 
	 * recepcionista, vigilante o bartender y de acuerdo a esto le asignar� el 
	 * salario fijo correspondiente.
	 * */
	public void asignarSalario() {
		if (this.getCargo().equals("chef")) {
			this.setSalario(200000);
		}
		if (this.getCargo().equals("recepcionista")) {
			this.setSalario(150000);
		}
		if ((this.getCargo().equals("vigilante")) || (this.getCargo().equals("bartender"))) {
			this.setSalario(160000);
		}
		bono();
	}
	
	protected void bono() {
		if(getCargo().equals("vigilante") || getCargo().equals("bartender") ) {
			int totalbono = (int) (getSalario() * 0.05);
			setSalario(getSalario() - totalbono);
		}else {
			int totalbono = (int) (getSalario() * 0.1);
			setSalario(getSalario() - totalbono);
		}
		
	}

}
