package gestorAplicacion.Funcionamiento;

import gestorAplicacion.*;
import java.util.Date;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import uiMain.*;

public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate fecha_de_ingreso;
	private LocalDate fecha_de_salida;
	// public boolean estado;//para pensar
	private Cliente cliente;
	// public Habitacion habitacion;
	
	//public static DateTimeFormatter convertidor = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reserva(String fecha_de_ingreso, String fecha_de_salida, Cliente cliente) {
		LocalDate fecha_ingresar = LocalDate.parse(fecha_de_ingreso);
		this.fecha_de_ingreso = fecha_ingresar;
		LocalDate fecha_salir = LocalDate.parse(fecha_de_salida);
		this.fecha_de_salida = fecha_salir;
		this.cliente = cliente;
		Recepcion.getHotel().asignarHabitacion(cliente);
		cliente.setReserva(true);
		Recepcion.getHotel().getReservas().add(this);

	}

	/// Setters y getters.

	public LocalDate getFecha_de_ingreso() {
		return fecha_de_ingreso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setFecha_de_ingreso(String fecha_de_ingreso) {
		LocalDate fecha_ingresar = LocalDate.parse(fecha_de_ingreso);
		this.fecha_de_ingreso = fecha_ingresar;
	}

	public LocalDate getFecha_de_salida() {
		return fecha_de_salida;
	}

	public void setFecha_de_salida(String fecha_de_salida) {
		LocalDate fecha_salir = LocalDate.parse(fecha_de_salida);
		this.fecha_de_ingreso = fecha_salir;
	}

	/// M�todos.
	public void reasignar_reserva(String nueva_fecha_ing, String nueva_fecha_sal) {
		cliente.setFecha_entrada(nueva_fecha_ing);
		cliente.setFecha_salida(nueva_fecha_sal);
	}

	public void cancelar_reserva(Cliente cliente) {
		cliente.getHabitacion().setDisponibilidadHab(true);
		// Recepcion.hotel.getHabitaciones().add(cliente.habitacion);
		int cap2 = Habitacion.getCapacidad2();
		int cap3 = Habitacion.getCapacidad3();
		int cap4 = Habitacion.getCapacidad4();
		int cap5 = Habitacion.getCapacidad5();
		int totalPersonas = 1 + cliente.getNumAcompanantes();

		if ((totalPersonas == 1 || totalPersonas == 2)) {
			Habitacion.setCapacidad2(cap2 + 1);
		} else if (totalPersonas == 3) {
			Habitacion.setCapacidad3(cap3 + 1);
		} else if (totalPersonas == 4) {
			Habitacion.setCapacidad4(cap4 + 1);
		} else if (totalPersonas == 5) {
			Habitacion.setCapacidad5(cap5 + 1);
		}
		cliente.setReserva(false);
		for (int i = 0; i < Recepcion.getHotel().getReservas().size(); i++) {
			if (Recepcion.getHotel().getReservas().get(i).cliente.equals(cliente)) {
				Recepcion.getHotel().getReservas().remove(Recepcion.getHotel().getReservas().get(i));
				Recepcion.getHotel().getClientes().get(i).setHabitacion(null);;
				Recepcion.getHotel().getHabitaciones().get(i).setCliente(null);
				Recepcion.getHotel().getClientes().remove(cliente);
				break;
		}
			break;
		}

	}

	public void cancelar_reserva() {
		Recepcion.getHotel().getClientes().remove(this.getCliente());
		Recepcion.getHotel().getReservas().remove(this);
		
	}
}
