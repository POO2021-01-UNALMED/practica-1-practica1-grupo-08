package uiMain;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import baseDatos.Defecto;
import baseDatos.Serializacion;
import gestorAplicacion.*;
import gestorAplicacion.Funcionamiento.*;
import gestorAplicacion.Personal.*;

public class Recepcion {
	public static Administrador ad1 = new Administrador("Luis", 134344);
	public static Hotel hotel = new Hotel();

	static Scanner sc = new Scanner(System.in);

	static long readLong() {
		return sc.nextLong();
	}

	static String readIn() {
		sc.nextLine();
		return sc.nextLine();
	}
	
	
	/*String fehca = "01/05/2021";
	DateFormat sdfe = new SimpleDateFormat("dd/MM/yyyy");  //////
	Date nuevo = sdfe.parse(fehca);
	String fechafi = sdfe.format(nuevo);
	System.out.println(fechafi);*/
	
	
	public static void main(String[] args) {
		//public static Hotel hotel = new Hotel();
		int opcion;

		/*Cliente cliente1 = new Cliente("Nora", 21345687, "27/05/2021", "03/06/2021", 2, 15000000, 1000656556);
		Cliente cliente2 = new Cliente("Fabio", 21356780, "02/06/2021", "06/06/2021", 3, 20000000);
		Cliente cliente3 = new Cliente("Yesenia", 21724520, "03/06/2021", "15/06/2021", 4, 25000000);
		Cliente cliente4 = new Cliente("Wilmar", 15670834, "28/05/2020", "07/06/2001", 4, 35000000, 4567234); ///// Solo																										///// titular.
		Cliente cliente5 = new Cliente("Sof�a", 4321689, "07/07/2020", "20/07/2020", 2, 15600000, 115467300);

		Habitacion hab1 = new Habitacion(103, 2);
		Habitacion hab2 = new Habitacion(202, 4);
		Habitacion hab3 = new Habitacion(101, 3);
		Habitacion hab4 = new Habitacion(303, 5);
		Habitacion hab5 = new Habitacion(205, 4);

		Empleado emp1 = new Empleado("Luis", 2489364,"vigilante", HorasExtras.DIURNA, 10);
		Empleado emp2 = new Mucama("Karla", 3544565, HorasExtras.DIURNADOMINICAL, 9);
		Empleado emp3 = new Empleado("Mario", 10595906,"Rececionista");*/
		
		Defecto.ObjetosDefecto();


		do {
			System.out.println("\nBienvenidos al hotel, �qu� acci�n desea realizar ahora?");
			System.out.println("1. Tomar una habitaci�n");
			System.out.println("2. Hacer una reserva");
			// cancelar reserva
			System.out.println("3. Elegir men� del restaurante");
			System.out.println("4. Elegir atracci�n");
			System.out.println("5. Mostrar ganancias netas");
			System.out.println("6. Dar salida a un cliente");
			System.out.println("7. Mostrar lista de clientes que se encuentren en el hotel");
			System.out.println("8. Terminar");
			System.out.println("Teclee su opci�n: ");
			opcion = (int) readLong();

			switch (opcion) {
			case 1:
				tomarHabitacion();
				break;
			case 2:
				hacerReserva();
				break;
			case 3:
				elegirMenu();
				break;
			case 4:
				elegirAtraccion();
				break; //// Poner terminar///
			case 5:
				gananciasNetas();
				break;
			case 6:
				salidaCliente();
				break;
			case 7:
				mostrarClientes();
				break;
			// AQU�//
			case 8:
				salirDelsistema();
				break;
			}
		} while (opcion != 8);
	}

	static void tomarHabitacion() {
		System.out.println("Ingrese C.C. del cliente: ");
		long cedula = readLong();
		Cliente clientenuevo = buscarCliente(cedula);
		hotel.asignarHabitacion(clientenuevo); // �Si no encuentra habitaci�n?
		if (clientenuevo.getHabitacion() == null) {
			System.out.println("No hay habitaciones disponibles,�desea hacer una reserva?");
			String res = readIn();// Debe responder si o no y sin tilde
			String cadenaNormalize = Normalizer.normalize(res.toLowerCase(), Normalizer.Form.NFD);
			String respuesta = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
			if (respuesta.equals("si")) {
				System.out.println("Ingrese su nueva fecha de ingreso en formato dd/mm/yyyy: ");
				String fecha = sc.nextLine();
				//LocalDate fecha_nueva = LocalDate.parse(fecha);
				System.out.println("Ingrese su nueva fecha de salida en formato dd/mm/yyyy: ");
				String fecha_s = sc.nextLine();
				//LocalDate fecha_nueva_salida = LocalDate.parse(fecha_s);
				System.out.println("ingreso: "+ fecha + " salida:" + fecha_s);
				Reserva reserva1 = new Reserva(fecha,fecha_s,clientenuevo);
				System.out.println("Reserva realizada con �xito");
				return;
			} else if (respuesta.equals("no")) {
				hotel.clientes.remove(clientenuevo);
				System.out.println("�Gracias por elegirnos, esperamos tener disponibilidad la pr�xima ocasi�n!");
			}
		}
		if (clientenuevo.getHabitacion() != null) {
			System.out.println("Su habitaci�n asignada es: " + clientenuevo.getHabitacion().numHabitacion);
		}
		

	}

	static void hacerReserva() {
		System.out.println("Ingrese C.C. del cliente: ");
		long cedula = readLong();// Desde el momento que se hace una reserva la habitacion queda ocupada
		Cliente clientenuevo = buscarCliente(cedula);
		Reserva reserva1 = new Reserva(clientenuevo.getFecha_entrada(), clientenuevo.getFecha_salida(), clientenuevo);
		if (clientenuevo.getHabitacion() == null) {
			System.out.println("No se encontraron habitaciones,�desea reasignar o cancelar la reserva?");
			String res = readIn();// Debe reasignar o cancelar
			
			String cadenaNormalize = Normalizer.normalize(res.toLowerCase(), Normalizer.Form.NFD);
			String respuesta = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");

			if (respuesta.equals("reasignar")) {
				System.out.println("Ingrese su nueva fecha de ingreso en formato dd/mm/yyyy: ");
				String fecha = sc.nextLine();
				LocalDate fecha_nueva = LocalDate.parse(fecha);
				System.out.println("Ingrese su nueva fecha de salida en formato dd/mm/yyyy: ");
				String fecha_s = sc.nextLine();
				LocalDate fecha_nueva_salida = LocalDate.parse(fecha_s);
				reserva1.reasignar_reserva(fecha_nueva.toString(), fecha_nueva_salida.toString());
				System.out.println("Reserva reasignada con �xito.");
			} else if (respuesta.equals("cancelar")) {///// Solo cancela si al intentar asignarle una habitaci�n no hay disponibles.
				System.out.println("�Gracias por elegirnos!");
				hotel.clientes.remove(clientenuevo);
			}
		}
		clientenuevo.setReserva(true);
		reserva1.setCliente(clientenuevo);
	}

	static void elegirMenu() {
		System.out.println("BIENVENIDO AL RESTAURANTE.");
		System.out.println("Ingrese C.C. del cliente: ");
		long cedula = readLong();
		Cliente cliente = buscarCliente(cedula);

		String respFinal;
		do {
			System.out.println(
					"�Desea ver la carta vegetariana o tradicional? Por favor, digite el n�mero correspondiente."
							+ "\n" + "1. Carta vegetariana." + "\n" + "2. Carta tradicional.");
			
			int opcionCarta = (int) readLong();
			
			if (opcionCarta == 1) {
				System.out.println("Carta vegetariana: \n" + "1. Espirales con setas y verduras. - $20000. \n"
						+ "2. Ensala de esp�rragos y reques�n - $18000. \n" + "3. Lasa�a vegetal - $15000. \n"
						+ "4. Alcachofas rellenas de quinoa - $22000. \n" + "5. Hamburguesa vegetariana - $15000. \n");
			} else if (opcionCarta == 2) {
				System.out.println("Carta tradicional: \n" + "1. Alitas orientales - $15000. \n"
						+ "2. Arroz atollado - $18000. \n" + "3. Bandeja paisa - $25000. \n"
						+ "4. Crema de champi�ones - $15000. \n" + "5. H�gado encebollado - $20000.");
			} else {
				System.out.println("D�gito ingresado incorrecto.");
				return;
			}

			System.out.println("Digite el n�mero que le corrresponde al platillo que desea.");
			int eleccion = (int) readLong();
			if (eleccion <= 0 || eleccion > 5) {
				System.out.println("D�gito ingresado inv�lido");
				return;
			}
			cliente.getServicio().tipoMenu(opcionCarta, eleccion, cliente);
			System.out.println("�Desea elegir otro platillo?");
			String respSalir = readIn();
			String cadenaNormalize = Normalizer.normalize(respSalir.toLowerCase(), Normalizer.Form.NFD);
			respFinal = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
		} while (respFinal.equals("si"));
		System.out.println("�Buen provecho!");
	}

	static void elegirAtraccion() {
		System.out.println("BIENVENIDO AL PARQUE DE DIVERSIONES.");
		System.out.println("Ingrese C.C. del cliente: ");
		long cedula = readLong();
		Cliente cliente = buscarCliente(cedula);

		String respFinal;
		do {
			System.out.println("Por favor, digite el n�mero correspondiente a la atracci�n que desea."
					+ "\n1. Monta�a rusa - $15000. \n" + "2. Paseo oscuro - $15000. \n"
					+ "3. Carritos chocones - $10000. \n" + "4. Piscina - $20000. \n"
					+ "5. Piscina de pelotas - $8000. \n" + "6. Carrusel - $8000. \n" + "7. Bungy - $10000. \n"
					+ "8. Barco pirata - $15000.");

			int eleccion = (int) readLong();
			if (eleccion <= 0 || eleccion > 8) {
				System.out.println("D�gito ingresado inv�lido");
				return;
			}
			cliente.getServicio().tipoAtraccion(eleccion, cliente);
			System.out.println("�Desea elegir otra atracci�n?");
			String respSalir = readIn();
			String cadenaNormalize = Normalizer.normalize(respSalir.toLowerCase(), Normalizer.Form.NFD);
			respFinal = cadenaNormalize.replaceAll("[^\\p{ASCII}]", "");
		} while (respFinal.equals("si"));
		System.out.println("�Disfrute del juego!");
	}

	public static void gananciasNetas() {
		System.out.println("Las ganancias netas del hotel hasta el momento son iguales a: " + hotel.gananciaNeta()+ "\n");
	}

	public static void salidaCliente() {
		tomarHabitacion(); //////////////////////Necesitamos las bases de datos.
		System.out.println("Ingrese C.C. del cliente para dar salida: ");
		long cedula = readLong();
		Cliente clientesalida = buscarCliente(cedula);
		clientesalida.getHabitacion().setDisponibilidadHab(true);
		System.out.println("�Gracias por visitarnos, vuelva pronto!");
		hotel.clientes.remove(clientesalida);
	}

	public static void mostrarClientes() {
		for (Cliente i : hotel.getClientes()) {
			if (i.isReserva() == true) {
			} else {
				System.out.println(i);
			}
		}
	}

	// Metodos de busqueda
	public static Cliente buscarCliente(long cedula) {
		Cliente uno = null;
		for (Cliente i : hotel.clientes) {
			if (cedula == i.getId()) {
				uno = i;
				break;
			}
		}if(uno == null) {
			System.out.println("Cliente no encontrado, ingrese una nueva c�dula: ");
			long c1 = readLong();
			buscarCliente(c1);
		}

		return uno;
	}
	// M�todo

	private static void salirDelsistema() {
		System.out.println("�Vuelva pronto!");
		Serializacion.serializacion(hotel);
		System.exit(0);
	}

}
