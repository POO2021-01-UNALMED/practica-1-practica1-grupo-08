package gestorAplicacion.Funcionamiento;

import java.io.Serializable;
import gestorAplicacion.Cliente;
import uiMain.Recepcion;

public class Servicio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gastosServicios;
	private Cliente cliente;

	public Servicio(Cliente cliente) {
		this.cliente = cliente;
		cliente.setServicio(this);
		Recepcion.hotel.getServicios().add(this);
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setGastosServicios(int valorGasto) {
		this.gastosServicios = valorGasto;
	}

	public int getGastosServicios() {
		return this.gastosServicios;
	}

	public void tipoMenu(int opcionCarta, int eleccion, Cliente cliente) {
		int valorTotalServicio = gastosServicios;

		if (opcionCarta == 1) {
			switch (eleccion) {
			case 1:
				valorTotalServicio = valorTotalServicio + 20000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 2:
				valorTotalServicio = valorTotalServicio + 18000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 3:
				valorTotalServicio = valorTotalServicio + 15000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 4:
				valorTotalServicio = valorTotalServicio + 22000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 5:
				valorTotalServicio = valorTotalServicio + 15000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;
			}
		} else if (opcionCarta == 2) {
			switch (eleccion) {
			case 1:
				valorTotalServicio = valorTotalServicio + 15000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 2:
				valorTotalServicio = valorTotalServicio + 18000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;

			case 3:
				valorTotalServicio = valorTotalServicio + 25000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;
			case 4:
				valorTotalServicio = valorTotalServicio + 15000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;
			case 5:
				valorTotalServicio = valorTotalServicio + 20000;
				cliente.getServicio().setGastosServicios(valorTotalServicio);
				break;
			}
		}
	}

	public void tipoAtraccion(int eleccion, Cliente cliente) {
		int valorTotalServicio = gastosServicios;

		switch (eleccion) {
		case 1:
			valorTotalServicio = valorTotalServicio + 15000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 2:
			valorTotalServicio = valorTotalServicio + 15000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 3:
			valorTotalServicio = valorTotalServicio + 10000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 4:
			valorTotalServicio = valorTotalServicio + 20000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 5:
			valorTotalServicio = valorTotalServicio + 8000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 6:
			valorTotalServicio = valorTotalServicio + 8000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 7:
			valorTotalServicio = valorTotalServicio + 10000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		case 8:
			valorTotalServicio = valorTotalServicio + 15000;
			cliente.getServicio().setGastosServicios(valorTotalServicio);
			break;

		}

	}

}
