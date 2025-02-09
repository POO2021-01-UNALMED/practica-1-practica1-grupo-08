package uiMain;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Optional;
import Errores.ExcepcionNoCliente;
import gestorAplicacion.Cliente;
import gestorAplicacion.Funcionamiento.Hotel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
//Clase Elegir Menu
/* Funcionalidad: Esta clase permite que al ser seleccionado en la barra de men� el item "Elegir men� del restaurante" un
 * cliente previamente registrado y que haya tomado una habitaci�n pueda elegir los platillos que desee 
 * */
public class ElegirMenu {
	Label criterio = new Label("C�dula: ");
	TextField campo = new TextField();
	Button enviar = new Button("Enviar");
	GridPane infoCed = new GridPane();

	public ElegirMenu() {
		ElegirM oyente = new ElegirM(campo);// Se le ingresa como par�metro el TextField campo(donde se le ingresa la
		// cedula)
		enviar.setOnAction(oyente);
		infoCed.addRow(0, criterio, campo, enviar);
		infoCed.setAlignment(Pos.CENTER);
		GridPane.setMargin(campo, new Insets(20,20,20,20));
		Font tipoletraTex = new Font("Times New Roman", 18);
		criterio.setFont(tipoletraTex);
	}

	public GridPane getElegirMenu() {
		return infoCed;
	}

	class ElegirM implements EventHandler<ActionEvent> {
		TextField campo;

		public ElegirM(TextField c) {
			campo = c;
		}

		public void handle(ActionEvent evento) {
			BuscarCliente oidor = new BuscarCliente(campo);
			try {
			oidor.handle();}
			catch(ExcepcionNoCliente e) {
				Alert sinCliente = new Alert(AlertType.ERROR);
				sinCliente.setTitle("Error");
				sinCliente.setHeaderText("Cliente no encontrado.");
				sinCliente.setContentText(e.getMessage());
				Optional<ButtonType> result = sinCliente.showAndWait();
				if (!result.isPresent()) {
					campo.clear();
				}
				else if (result.get() == ButtonType.OK) {
					campo.clear();
			}
			}
			Cliente clienteNuevo = oidor.getBuscarCliente();
			if (clienteNuevo == null) {
				return;
			}

			if (clienteNuevo.getHabitacion() == null || clienteNuevo.isReserva() == true) {
				Alert noHospedado = new Alert(AlertType.INFORMATION);
				noHospedado.setTitle("Informaci�n");
				noHospedado.setHeaderText("Debes estar hospedado en el hotel para acceder a estos servicios.");
				noHospedado.setContentText(
						"Si desea acceder a nuestros servicios le invitamos a dirigirse al men� y tomar una habitaci�n.");																																																								
				Optional<ButtonType> resulta = noHospedado.showAndWait();
				if (!resulta.isPresent()) {
					return;
				}
				else if (resulta.get() == ButtonType.OK) {
					campo.clear();
					return;
				}

			//Si el cliente cumple se encuentra en el hotel entonces tiene dos tipos de men�s: men� vegetariano y men� tradicional,
			//en ambos men�s se le despliega la lista de platos disponibles para su elecci�n.
			//Luego de elegir platos la cuenta del cliente ser� modificada con el valor de los platos elegidos.	
			} else if (clienteNuevo != null) {
				infoCed.getChildren().clear();

				Funcionalidades.titulo.setText("Opciones de tipo de carta y platos");
				Funcionalidades.descripcion.setText("Elija a continuaci�n el tipo de carta y los platos que desee:");
				String tipomenu[] = { "Carta vegetariana", "Carta tradicional" };
				ComboBox<String> combomenu = new ComboBox<String>(FXCollections.observableArrayList(tipomenu));
				combomenu.setPromptText("Tipo de carta");
				oyenteComboM elemenu = new oyenteComboM(combomenu, clienteNuevo);
				combomenu.setOnAction(elemenu);
				infoCed.addRow(0, combomenu);
				GridPane.setMargin(combomenu, new Insets(20,20,20,20));

			}
		}

	}

	class oyenteComboM implements EventHandler<ActionEvent> {
		Cliente clienteNuevo;
		ComboBox<String> combomenu;
		VBox eleccionmenu = new VBox(10);
		Label cabecera;
		Button confirmar = new Button("Confirmar elecci�n");
		CheckBox op1= new CheckBox();;
		CheckBox op2 = new CheckBox();
		CheckBox op3 = new CheckBox();
		CheckBox op4 = new CheckBox();
		CheckBox op5 = new CheckBox();
		Label respuesta;
		

		public oyenteComboM(ComboBox<String> combomenu, Cliente cl) {
			clienteNuevo = cl;
			this.combomenu = combomenu;
			infoCed.addRow(1, eleccionmenu);
			respuesta = new Label("No ha seleccionado ning�n plato");
			respuesta.setWrapText(true);
			respuesta.setTextAlignment(TextAlignment.CENTER);
			cabecera = new Label("Seleccione los platos que desea:");
			Font tipoletra = new Font("Times New Roman", 18);
			respuesta.setFont(tipoletra);
			cabecera.setFont(tipoletra);
			op1.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 18px ;");
			op2.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 18px ;");
			op3.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 18px ;");
			op4.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 18px ;");
			op5.setStyle("-fx-font-family: 'Times New Roman' ; -fx-font-size: 18px ;");
		}

		public void handle(ActionEvent e) {
			eleccionmenu.getChildren().clear();
			eleccionmenu.getChildren().addAll(cabecera, op1, op2, op3, op4, op5, respuesta, confirmar);
			eleccionmenu.setPadding(new Insets(10,10,10,10));
			eleccionmenu.setPrefSize(400,300);
			ArrayList<Integer> opcarta = new ArrayList<Integer>();
			confirmar.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {

					if (opcarta.size() == 0) {
						Alert noselect = new Alert(AlertType.WARNING);
						noselect.setTitle("Advertencia");
						noselect.setHeaderText("Por favor selecciona como m�nimo un plato.");
						noselect.show();
						return;

					}
					Alert confirmacion = new Alert(AlertType.CONFIRMATION);
					confirmacion.setTitle("Confirmaci�n.");
					confirmacion.setHeaderText("Elecci�n de platos");
					confirmacion.setContentText("�Deseas confirmar el pedido?");
					ButtonType si = new ButtonType("S�", ButtonBar.ButtonData.YES);
					ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
					confirmacion.getButtonTypes().setAll(si, no);
					Optional<ButtonType> respuesta = confirmacion.showAndWait();
					if (!respuesta.isPresent()) {
						return;
					}
					if (respuesta.get().equals(confirmacion.getButtonTypes().get(1))) {
						// Manda a escoger platillo
					} else if (respuesta.get().equals(confirmacion.getButtonTypes().get(0))) {
						
						//Se aumenta la cuenta del cliente con el valor de los platillos.
						for(int i = 0; i< opcarta.size();i++) {
							clienteNuevo.getServicio().tipoMenu(1, opcarta.get(i), clienteNuevo);
						}
						opcarta.clear();
						Alert pedido = new Alert(AlertType.INFORMATION);
						pedido.setTitle("Informaci�n");
						pedido.setHeaderText(null);
						pedido.setContentText("Disfrute su plato. �Buen provecho!");
						Optional<ButtonType> resultado = pedido.showAndWait();
						if (!resultado.isPresent()) {
							return;
						}
						if (resultado.get() == ButtonType.OK) {
							op1.setSelected(false);
							op2.setSelected(false);
							op3.setSelected(false);
							op4.setSelected(false);
							op5.setSelected(false);
						}
					}
				}
			});
           //Opciones de la carta vegetariana
			if (combomenu.getValue().equals("Carta vegetariana")) {
				op1.setText("Espirales con setas y verduras. - $20000");
				op2.setText("Ensala de esp�rragos y reques�n - $18000");
				op3.setText("Lasa�a vegetal - $15000");
				op4.setText("Alcachofas rellenas de quinoa - $22000");
				op5.setText("Hamburguesa vegetariana - $15000");

				// Acciones:
				op1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op1.isSelected()) {
							respuesta.setText("Espirales con setas y verduras seleccionados.");
							opcarta.add(1);
						} else {

							respuesta.setText("Quitaste el plato: Espirales con setas y verduras");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 1) {
									opcarta.remove(i);
								}
							}

						}
					}
				});

				op2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op2.isSelected()) {
							respuesta.setText("Ensala de esp�rragos y reques�n seleccionados.");
							opcarta.add(2);
						} else {
							respuesta.setText("Quitaste el plato: Ensala de esp�rragos y reques�n");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 2) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op3.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op3.isSelected()) {
							respuesta.setText("Lasa�a vegetal seleccionada.");
							opcarta.add(3);
						} else {
							respuesta.setText("Quitaste el plato: Lasa�a vegetal");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 3) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op4.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op4.isSelected()) {
							respuesta.setText("Alcachofas rellenas de quinoa seleccionadas.");
							opcarta.add(4);
						} else {
							respuesta.setText("Quitaste el plato: Alcachofas rellenas de quinoa");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 4) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op5.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op5.isSelected()) {
							respuesta.setText("Hamburguesa vegetariana seleccionados.");
							opcarta.add(5);
						} else {
							respuesta.setText("Quitaste el plato: Hamburguesa vegetariana");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 5) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				//Opciones de la carta tradicional

			} else if (combomenu.getValue().equals("Carta tradicional")) {
				op1.setText("Alitas orientales - $15000.");
				op2.setText("Arroz atollado - $18000.");
				op3.setText("Bandeja paisa - $25000.");
				op4.setText("Crema de champi�ones - $15000.");
				op5.setText("H�gado encebollado - $20000.");

				// Acciones:
				op1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op1.isSelected()) {
							respuesta.setText("Espirales con setas y verduras seleccionados.");
							opcarta.add(1);
						} else {

							respuesta.setText("Quitaste el plato: Espirales con setas y verduras");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 1) {
									opcarta.remove(i);
								}
							}

						}
					}
				});

				op2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op2.isSelected()) {
							respuesta.setText("Ensala de esp�rragos y reques�n seleccionada.");
							opcarta.add(2);
						} else {
							respuesta.setText("Quitaste el plato: Ensala de esp�rragos y reques�n");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 2) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op3.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op3.isSelected()) {
							respuesta.setText("Lasa�a vegetal seleccionada.");
							opcarta.add(3);
						} else {
							respuesta.setText("Quitaste el plato: Lasa�a vegetal");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 3) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op4.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op4.isSelected()) {
							respuesta.setText("Alcachofas rellenas de quinoa seleccionadas.");
							opcarta.add(4);
						} else {
							respuesta.setText("Quitaste el plato: Alcachofas rellenas de quinoa");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 4) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

				op5.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						if (op5.isSelected()) {
							respuesta.setText("Hamburguesa vegetariana seleccionada.");
							opcarta.add(5);
						} else {
							respuesta.setText("Quitaste el plato: Hamburguesa vegetariana");

							for (int i = 0; i < opcarta.size(); i++) {
								if (opcarta.get(i) == 5) {
									opcarta.remove(i);
								}
							}
						}
					}
				});

			}

		}
	}

}
