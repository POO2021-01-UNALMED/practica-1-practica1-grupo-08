package uiMain;

import java.util.Optional;

import Errores.ExcepcionMostrarClientes;
import Errores.ExcepcionNoGanancias;
import baseDatos.Deserializacion;
import gestorAplicacion.Cliente;
import gestorAplicacion.Funcionamiento.*;
import gestorAplicacion.Personal.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//Clase Funcionalidades:
/* Componentes : Panel principal que ir� cambiando de acuerdo a la funcionalidad escogida, barra de men� que contiene cada una de 
 * las funcionalidades, el atributo estandar de tipo Scene usado para cambiar entre ventanas y todos los oidores de cada una de las 
 * funcionalidades escogidas a trav�s de la barra de men�.
 * Funcionalidad: Esta clase permitir� efectuar cada una de las acciones que un usuario puede realizar en el sistema del hotel, dichas acciones se encuentran en la barra del men� y se describen en cada de
 * sus clases. 
 * */
public class Funcionalidades {
	public static VBox principal = new VBox(20);
	public MenuBar barramenu = new MenuBar();;
	public static Label titulo;
	public static Label descripcion;
	public static Scene estandar = new Scene(principal, 800, 550);

    //Esta es la escena que aparecer� luego de abrer presionado el boton Men� principal de la clase GUI
	//permitir�, ir mostrando en pantalla cada una de las funcionalidades que el usuario desea implementar.
	public void crearScene() {

		Menu inicio = new Menu("Inicio");
		MenuItem regresar = new MenuItem("Regresar");
		regresar.setOnAction(new Inicio());
		inicio.getItems().add(regresar);

		Menu archivo = new Menu("Archivo");
		MenuItem aplicacion = new MenuItem("Aplicaci�n");
		aplicacion.setOnAction(new Eventos());
		MenuItem salir = new MenuItem("Salir");
		salir.setOnAction(new Eventos());
		archivo.getItems().addAll(aplicacion, salir);

		Menu procesos = new Menu("Procesos y consultas");
		MenuItem fun1 = new MenuItem("Tomar habitaci�n");
		fun1.setOnAction(new Eventos());
		MenuItem fun2 = new MenuItem("Cancelar reserva");
		fun2.setOnAction(new Eventos());
		MenuItem fun3 = new MenuItem("Elegir men� del restaurante");
		fun3.setOnAction(new Eventos());
		MenuItem fun4 = new MenuItem("Elegir atracci�n");
		fun4.setOnAction(new Eventos());
		MenuItem fun5 = new MenuItem("Mostrar ganancias netas");
		fun5.setOnAction(new Eventos());
		MenuItem fun6 = new MenuItem("Dar salida a un cliente");
		fun6.setOnAction(new Eventos());
		MenuItem fun7 = new MenuItem("Mostrar clientes");
		fun7.setOnAction(new Eventos());
		procesos.getItems().addAll(fun1, fun2, fun3, fun4, fun5, fun6, fun7);

		Menu ayuda = new Menu("Ayuda");
		MenuItem acerca = new MenuItem("Acerca de");
		acerca.setOnAction(new Eventos());
		ayuda.getItems().add(acerca);
		barramenu.getMenus().addAll(inicio, archivo, procesos, ayuda);

		titulo = new Label("Bienvenido al hotel.");
		Font tipoletraTit = new Font("Times New Roman", 30);
		titulo.setFont(tipoletraTit);
		titulo.setTextFill(Color.web("#873600"));
		titulo.setTextAlignment(TextAlignment.CENTER);

		descripcion = new Label(
				"En la barra superior encontrar�s los servicios que tenemos disponibles, esperamos que sean "
						+ "de tu agrado.");
		Font tipoletraTex = new Font("Times New Roman", 18);
		descripcion.setFont(tipoletraTex);
		descripcion.setWrapText(true);
		descripcion.setTextAlignment(TextAlignment.CENTER);

		Image imagen = new Image(getClass().getResourceAsStream("images.jpg"), 450, 350, false, false);
		Label label = new Label("", new ImageView(imagen));
		principal.getChildren().addAll(barramenu, titulo, descripcion, label);
		principal.setStyle("-fx-background-color:#FCF3CF ;");
		principal.setAlignment(Pos.TOP_CENTER);
	}

	public Scene getEscenaFun() {
		crearScene();
		return estandar;
	}

}
//Este clase de tipo EventHandler nos indica que funcionalidad se ha elegido para proceder de acuerdo a esto.

class Eventos implements EventHandler<ActionEvent> {
	public void handle(ActionEvent e) {
		MenuItem opcion = (MenuItem) e.getSource();
		if (opcion.getText().equals("Tomar habitaci�n")) {
			GridPane hab = new TomarHabitacion().getTomarHabitacion();
			Funcionalidades.titulo.setText(("Tomar una habitaci�n."));
			Funcionalidades.descripcion
					.setText("Para que le sea asignada una habitaci�n por favor ingrese su n�mero de c�dula.");
			Funcionalidades.principal.getChildren().add(hab);
			Funcionalidades.principal.getChildren().remove(3);

		} else if (opcion.getText().equals("Cancelar reserva")) {
			GridPane reserva = new CancelarReserva().getCancelarReserva();
			Funcionalidades.titulo.setText(("Cancelar una reserva."));
			Funcionalidades.descripcion.setText("Para cancelar su reserva por favor ingrese su n�mero de c�dula.");
			Funcionalidades.principal.getChildren().add(reserva);
			Funcionalidades.principal.getChildren().remove(3);
		} else if (opcion.getText().equals("Dar salida a un cliente")) {
			GridPane salida = new DarSalida().getDarSalida();
			Funcionalidades.titulo.setText(("Salida del hotel"));
			Funcionalidades.descripcion.setText("Para retirarse del hotel, por favor ingrese su c�dula.");
			Funcionalidades.principal.getChildren().add(salida);
			Funcionalidades.principal.getChildren().remove(3);
		} else if (opcion.getText().equals("Elegir men� del restaurante")) {
			GridPane elemenu = new ElegirMenu().getElegirMenu();
			Funcionalidades.titulo.setText(("Elegir men� del restaurante"));
			Funcionalidades.descripcion.setText("Para elegir el men� que desea, por favor ingrese su c�dula.");
			Funcionalidades.principal.getChildren().remove(3);
			Funcionalidades.principal.getChildren().add(elemenu);
		} else if (opcion.getText().equals("Elegir atracci�n")) {
			GridPane eleatraccion = new ElegirAtraccion().getElegirAtraccion();
			Funcionalidades.titulo.setText(("Elegir atracci�n"));
			Funcionalidades.descripcion.setText("Para elegir la atracci�n que desea, por favor ingrese su c�dula.");
			Funcionalidades.principal.getChildren().remove(3);
			Funcionalidades.principal.getChildren().add(eleatraccion);
		} else if (opcion.getText().equals("Mostrar ganancias netas")) {
			int total = 0;
			for (Cliente i : Hotel.getClientes()) {
				total += i.getCuentaFinal();
			}
			
			int ganancias = 0;
			try {
				ganancias = Hotel.gananciaNeta();
				Funcionalidades.titulo.setText(("Informe de ganancias netas."));
				Funcionalidades.descripcion.setText("Administrador " + Hotel.getAd1().getNombre()
						+ ", a continuaci�n se detalla los egresos e ingresos del hotel hasta el momento: ");
				TextArea info = new TextArea();
				info.setWrapText(true);
				info.setText("- Ingresos por cuentas finales de clientes: " + total + "\n" + "\n"
						+ "- Egresos por pago de salarios a empleados: " + Hotel.getAd1().pagarSalario() + "\n" + "\n"
						+ "- Ganancias netas: " + Hotel.getGanancias());
				info.setEditable(false);
				info.setPrefSize(50, 150);
				Funcionalidades.principal.getChildren().add(info);
				Funcionalidades.principal.getChildren().remove(3);
				VBox.setMargin(Funcionalidades.principal.getChildren().get(3), new Insets(10, 10, 10, 10));
			} catch (ExcepcionNoGanancias e4) {				
				Alert sinGanancias = new Alert(AlertType.WARNING);
				sinGanancias.setTitle("Advertencia");
				sinGanancias.setContentText(e4.getMessage());
				Optional<ButtonType> result = sinGanancias.showAndWait();
				if (!result.isPresent()) {
				} else if (result.get() == ButtonType.OK) {					
				}
			}

		} else if (opcion.getText().equals("Mostrar clientes")) {
			Funcionalidades.principal.getChildren().remove(3);
			Funcionalidades.titulo.setText("Mostrar clientes");
			Funcionalidades.descripcion.setText("A continuaci�n se presenta la lista de clientes activos en el hotel:");
			try{
				Hotel.mostrarCliente();
				ListView<String> clientes = new ListView<String>();
				for (Cliente i : Hotel.getClientes()) {
					if (i.getHabitacion() != null && i.isReserva() == false) {
						clientes.getItems().add("Cliente identificado con " + i.getId()
								+ ", hospedado en la habitaci�n " + i.getHabitacion().getNumhabitacion() + ".");
						}
					}
				Funcionalidades.principal.getChildren().add(clientes);
				VBox.setMargin(Funcionalidades.principal.getChildren().get(3), new Insets(20, 20, 20, 20));				
			}catch(ExcepcionMostrarClientes cl) {
				Alert nulo = new Alert(AlertType.WARNING);
				nulo.setTitle("Advertencia");
				nulo.setHeaderText("Sin clientes");
				nulo.setContentText(cl.getMessage());
				Optional<ButtonType> resulta = nulo.showAndWait();
				if (!resulta.isPresent()) {		
					GUI.ventana.setScene(Funcionalidades.estandar);
					Funcionalidades.titulo.setText("Bienvenido al hotel.");
					Funcionalidades.descripcion.setText(
							"En la barra superior encontrar�s los servicios que tenemos disponibles,esperamos que sean de tu agrado.");
					Image imagen = new Image(getClass().getResourceAsStream("images.jpg"), 350, 250, false,
							false);
					Label label = new Label("", new ImageView(imagen));
					Funcionalidades.principal.getChildren().addAll(label);
				}
				else if (resulta.get() == ButtonType.OK) {
					GUI.ventana.setScene(Funcionalidades.estandar);
					Funcionalidades.titulo.setText("Bienvenido al hotel.");
					Funcionalidades.descripcion.setText(
							"En la barra superior encontrar�s los servicios que tenemos disponibles,esperamos que sean de tu agrado.");
					Image imagen = new Image(getClass().getResourceAsStream("images.jpg"), 350, 250, false,
							false);
					Label label = new Label("", new ImageView(imagen));
					Funcionalidades.principal.getChildren().addAll(label);
				}
			}
		}
					
		else if (opcion.getText().equals("Aplicaci�n")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Descripci�n de la aplicaci�n.");
			alert.setHeaderText("Informaci�n hotel.");
			alert.setContentText("La aplicaci�n permite realizar las diferentes funciones que se presentan en el hotel,"
					+ "tales como el ingreso de los clientes, la selecci�n de la habitaci�n que se acomode a sus necesidades,"
					+ "la variedad de men�s que pueden elegir y las atracciones disponibles para disfrutar de la estad�a. "
					+ "\n" + "\n"
					+ "Adem�s permite el acceso del personal encargado de estos procesos para un correcto funcionamiento del "
					+ "hotel, entre ellos se destacan al administrador, encargado de pagar el salario de los empleados incluyendo "
					+ "las horas extras que estos validen, las mucamas encargadas de mantener las habitaciones en orden y disponibles "
					+ "cuando se requiera  y el recepcionista quien tiene el control de la entrada y salida de los clientes y del hotel "
					+ "en general.");
			alert.getDialogPane().setStyle("-fx-font-size: 18 ;-fx-font-family: 'Times New Roman' ; -fx-background-color: #F5F2BA ;");
			alert.show();

		} else if (opcion.getText().equals("Acerca de")) {
			Alert nombres = new Alert(AlertType.INFORMATION);
			nombres.setTitle("Creadores");
			nombres.setHeaderText("Ximena Casta�eda Ochoa \nYojan Andr�s Alcar�z P�rez \nVer�nica Seguro Varela");
			nombres.setGraphic(new ImageView(
					new Image(getClass().getResourceAsStream("equipo.png"), 50, 50, false, false)));
			nombres.getDialogPane().setStyle("-fx-font-size: 18;-fx-font-family: 'Times New Roman',25 ; -fx-background-color: #F5F2BA ;");

			Optional<ButtonType> resulta = nombres.showAndWait();
			if (!resulta.isPresent()) {
			} else if (resulta.get() == ButtonType.OK) {
				GUI.ventana.setScene(Funcionalidades.estandar);
				Funcionalidades.titulo.setText("Bienvenido al hotel.");
				Funcionalidades.descripcion.setText(
						"En la barra superior encontrar�s los servicios que tenemos disponibles,esperamos que sean de tu agrado.");
			}

		}

		else if (opcion.getText().equals("Salir")) {
			Image imagen = new Image(getClass().getResourceAsStream("images.jpg"), 350, 250, false, false);
			GUI.label.setGraphic(new ImageView(imagen));
			GUI.label.setText("");
			GUI.ventana.setScene(GUI.escena1);
		}
	}
}
//Esta clase en particular permite volver a la pantalla principal sin importar que funcionalidad est�s implementando.
class Inicio implements EventHandler<ActionEvent> {
	public void handle(ActionEvent e) {
		GUI.ventana.setScene(Funcionalidades.estandar);
		Funcionalidades.titulo.setText("Bienvenido al hotel.");
		Font tipoletraTit = new Font("Times New Roman", 30);
		Funcionalidades.titulo.setFont(tipoletraTit);
		Funcionalidades.titulo.setTextFill(Color.web("#873600"));
		Funcionalidades.titulo.setTextAlignment(TextAlignment.CENTER);

		Funcionalidades.descripcion.setText(
				"En la barra superior encontrar�s los servicios que tenemos disponibles,esperamos que sean de tu agrado.");
		Font tipoletraTex = new Font("Times New Roman", 18);
		Funcionalidades.descripcion.setFont(tipoletraTex);
		Funcionalidades.descripcion.setWrapText(true);
		Funcionalidades.descripcion.setTextAlignment(TextAlignment.CENTER);

		Funcionalidades.principal.getChildren().remove(3);
		Image imagen = new Image(getClass().getResourceAsStream("images.jpg"), 450, 350, false, false);
		Label label = new Label("", new ImageView(imagen));
		Funcionalidades.principal.getChildren().addAll(label);
	}
}
