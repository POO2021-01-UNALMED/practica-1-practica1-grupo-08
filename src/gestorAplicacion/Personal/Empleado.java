package gestorAplicacion.Personal;

import gestorAplicacion.Persona;
import gestorAplicacion.Funcionamiento.*;
import uiMain.Recepcion;

import java.io.Serializable;
import java.util.ArrayList;
/*Autores: Ver�nica Seguro Varela. 
 * Componentes: Atributos,constructores, un m�todo abstracto, m�todos gets y sets,
 * m�todos asignarSalario(), pagoHorasExtras().   
 * Finalidad: Clase abstracta en donde se busca que los tipos de 
 * empleados hereden la mayor�a de estos m�todos y atributos, esta superclase busca 
 * crear empleados que especifiquen si han pagado horas extras y la cantidad horas 
 * pagadas para modificar su salario, dicha informaci�n ser� utilizada para ver 
 * la ganancia neta del Hotel. 
 * Cada empleado que sea creado se agregar� a la lista de empleados de la clase
 * Hotel  
 * */

public abstract class Empleado implements Persona, Serializable {
    //ATRIBUTOS
	// El siguiente atributo es necesario para la serizalizaci�n de las instancias de esta clase.
	private static final long serialVersionUID = 1L;
    
	private String nombre;
    private long id;
	private String cargo;
	private int salario;
	private HorasExtras horasextras; /*Atributo de la clase enumerada HorasExtras 
	que ser� utilizada en caso de que el empleado haya trabajado por fuera de su
	jornada laboral, pueden ser horas diurnas, nocturnas, diurnas dominicales y 
	nocturnas dominicales, cada tipo de hora extra tiene un valor diferente. */
    private int cantidadHoras;

    // CONSTRUCTOR VAC�O.
    public Empleado() {
    	
    }

	// CONSTRUCTOR 1
    /* Este constructor ser� usado en el caso de que el empleados tenga horas 
     * extras por cobrar,deber� especificar el tipo de hora extra pagada y 
     * la cantidad horas extras trabajadas esto con la finalidad de modificar
     * su salario y aumentarlo.
     * 
     * Cabe aclarar que el empleado s�lo podr� tener un tipo de hora extra ya sea 
     * diurna, nocturna, diurna dominical o nocturna dominical.
     * */
    public Empleado(String nombre, long id, String cargo, HorasExtras horasextras, int cantidadHoras) {
    	this(nombre,id,cargo.toLowerCase()); /* Se usa el m�todo toLowerCase() dado que
	se necesita que el cargo del empleado est� en min�scula para un correcto
	funcionamiento del m�todo asignarSalario() de la clase OficiosVarios.
    	 */ 
    	this.horasextras = horasextras;
    	this.cantidadHoras = cantidadHoras;
    	this.asignarSalario();
    	this.pagoHorasExtras();
    }
	/* CONSTRUCTOR 2
	 * Este ser� usado en el caso en que el empleado no tenga horas extras que
	 * cobrar y por lo tanto su salario no ser� modificado.
	 * */
	public Empleado(String nombre, long id, String cargo) {
		this.nombre = nombre;
		this.id = id;
		this.cargo = cargo.toLowerCase();
		this.asignarSalario();
		Hotel.getEmpleados().add(this);
	}
	// M�TODOS
	
	/* Las clases creadas a partir de esta deber�n implementar este m�todo para identificar
	 * el tipo de empleado y de acuerdo a esto asignarle el salario correspondiente.*/
	abstract void asignarSalario(); 

	/* Este m�todo ser� el encargado de realizar el pago de horas extras y aumentar
	 * dicho pago en el salario de los empleados que en su efecto hayan extendido su
	 * jornada laboral (empleados creados con el constructor 1).
	 * */
	protected void pagoHorasExtras() {
		if (horasextras.getPrecioHora() == 4731) {
			salario = salario + (4731 * (cantidadHoras));
		} else if (horasextras.getPrecioHora() == 6624) {
			salario = salario + 6624 * (cantidadHoras);
		} else if (horasextras.getPrecioHora() == 7570) {
			salario = salario + 7570 * (cantidadHoras);
		} else if (horasextras.getPrecioHora() == 9463) {
			salario = salario + 9463 * (cantidadHoras);
		}
	}
	
	protected void bono() {
		int totalbono = (int) (salario * 0.1);
		salario = salario - totalbono;
	}
	
	
	// M�TODOS GET Y SET: para el acceso y modificaci�n (cuando sea necesario) de los atributos.
	public String getNombre() {
		return nombre;
	}

	public long getId() {
		return id;
	}
	
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public HorasExtras getHorasextras() {
		return horasextras;
	}

	public int getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(int cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}	
	
}
